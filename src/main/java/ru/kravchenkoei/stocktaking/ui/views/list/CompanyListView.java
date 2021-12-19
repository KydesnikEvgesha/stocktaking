package ru.kravchenkoei.stocktaking.ui.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ru.kravchenkoei.stocktaking.data.model.Company;
import ru.kravchenkoei.stocktaking.data.service.CompanyService;
import ru.kravchenkoei.stocktaking.ui.views.MainLayout;
import ru.kravchenkoei.stocktaking.ui.views.components.forms.CompanyForm;


@PageTitle("Производители")
@Route(value = "/company", layout = MainLayout.class)
public class CompanyListView extends VerticalLayout {

    Grid<Company> companyGrid = new Grid<>();
    TextField filterText = new TextField();
    CompanyForm companyForm;
    CompanyService companyService;

    public CompanyListView(CompanyService companyService) {
        this.companyService = companyService;

        setSpacing(false);
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(companyGrid, companyForm);
        content.setFlexGrow(2, companyGrid);
        content.setFlexGrow(1, companyForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        companyForm = new CompanyForm();
        companyForm.setWidth("25em");
        companyForm.addListener(CompanyForm.SaveEvent.class, this::saveCompany);
        companyForm.addListener(CompanyForm.DeleteEvent.class, this::deleteCompany);
        companyForm.addListener(CompanyForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveCompany(CompanyForm.SaveEvent event) {
        companyService.saveCompany(event.getCompany());
        updateList();
        closeEditor();
    }

    private void deleteCompany(CompanyForm.DeleteEvent event) {
        companyService.deleteCompany(event.getCompany().getId());
        updateList();
        closeEditor();
    }

    public void editCompany(Company company) {
        if (company == null) {
            closeEditor();
        } else {
            companyForm.setCompany(company);
            companyForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        companyForm.setCompany(null);
        companyForm.setVisible(false);
        removeClassName("editing");
    }

    private void addCompany() {
        companyGrid.asSingleSelect().clear();
        editCompany(new Company());
    }


    public void configureGrid(){
        companyGrid.addClassName("contact-grid");
        companyGrid.setSizeFull();
        companyGrid.addColumn(Company::getName).setHeader("Наименованик компании");
        companyGrid.addColumn(Company::getAddress).setHeader("Адрес компании");
        companyGrid.getColumns().forEach(col -> col.setAutoWidth(true));

        companyGrid.asSingleSelect().addValueChangeListener(event ->
                editCompany(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по имени...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addCompanyButton = new Button("Добавить компанию");
        addCompanyButton.addClickListener(click -> addCompany());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addCompanyButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        companyGrid.setItems(companyService.searchCompanies(filterText.getValue()));
    }
}
