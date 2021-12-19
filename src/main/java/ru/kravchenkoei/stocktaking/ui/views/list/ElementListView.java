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
import ru.kravchenkoei.stocktaking.data.model.Element;
import ru.kravchenkoei.stocktaking.data.service.CompanyService;
import ru.kravchenkoei.stocktaking.data.service.ElementService;
import ru.kravchenkoei.stocktaking.data.service.TypeService;
import ru.kravchenkoei.stocktaking.ui.views.MainLayout;
import ru.kravchenkoei.stocktaking.ui.views.components.forms.ElementForm;

@PageTitle("Оборудование")
@Route(value = "/element", layout = MainLayout.class)
public class ElementListView extends VerticalLayout {
    Grid<Element> elementGrid = new Grid<>();
    TextField filterText = new TextField();
    ElementForm elementForm;
    ElementService elementService;
    CompanyService companyService;
    TypeService typeService;

    public ElementListView(ElementService elementService,
                           CompanyService companyService,
                           TypeService typeService) {
        this.elementService = elementService;
        this.companyService = companyService;
        this.typeService = typeService;

        setSpacing(false);
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(elementGrid, elementForm);
        content.setFlexGrow(2, elementGrid);
        content.setFlexGrow(1, elementForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        elementForm = new ElementForm(companyService.searchCompanies(""), typeService.searchTypes(""));
        elementForm.setWidth("25em");
        elementForm.addListener(ElementForm.SaveEvent.class, this::saveElement);
        elementForm.addListener(ElementForm.DeleteEvent.class, this::deleteElement);
        elementForm.addListener(ElementForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveElement(ElementForm.SaveEvent event) {
        elementService.saveElement(event.getElement());
        updateList();
        closeEditor();
    }

    private void deleteElement(ElementForm.DeleteEvent event) {
        elementService.deleteElement(event.getElement().getId());
        updateList();
        closeEditor();
    }

    public void editElement(Element element) {
        if (element == null) {
            closeEditor();
        } else {
            elementForm.setElement(element);
            elementForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        elementForm.setElement(null);
        elementForm.setVisible(false);
        removeClassName("editing");
    }

    private void addElement() {
        elementGrid.asSingleSelect().clear();
        editElement(new Element());
    }


    public void configureGrid(){
        elementGrid.addClassName("contact-grid");
        elementGrid.setSizeFull();
        elementGrid.addColumn(Element::getName).setHeader("Наименование");
        elementGrid.addColumn(element -> element.getCompany().getName()).setHeader("Производитель");
        elementGrid.addColumn(element -> element.getType().getName()).setHeader("Тип запчасти");
        elementGrid.getColumns().forEach(col -> col.setAutoWidth(true));

        elementGrid.asSingleSelect().addValueChangeListener(event ->
                editElement(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по имени...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addElementButton = new Button("Добавить продукцию");
        addElementButton.addClickListener(click -> addElement());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addElementButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        elementGrid.setItems(elementService.searchElements(filterText.getValue()));
    }
}
