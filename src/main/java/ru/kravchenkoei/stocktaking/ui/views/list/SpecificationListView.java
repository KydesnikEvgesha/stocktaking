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
import ru.kravchenkoei.stocktaking.data.model.Specification;
import ru.kravchenkoei.stocktaking.data.service.SpecificationService;
import ru.kravchenkoei.stocktaking.data.service.TypeService;
import ru.kravchenkoei.stocktaking.ui.views.MainLayout;
import ru.kravchenkoei.stocktaking.ui.views.components.forms.SpecificationForm;

@PageTitle("Справочник характеристик")
@Route(value = "/specdict", layout = MainLayout.class)
public class SpecificationListView extends VerticalLayout {
    Grid<Specification> specGrid = new Grid<>();
    TextField filterText = new TextField();
    SpecificationForm specificationForm;
    SpecificationService specificationService;
    TypeService typeService;

    public SpecificationListView(SpecificationService specificationService,
                           TypeService typeService) {
        this.specificationService = specificationService;
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
        HorizontalLayout content = new HorizontalLayout(specGrid, specificationForm);
        content.setFlexGrow(2, specGrid);
        content.setFlexGrow(1, specificationForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        specificationForm = new SpecificationForm(typeService.searchTypes(""));
        specificationForm.setWidth("25em");
        specificationForm.addListener(SpecificationForm.SaveEvent.class, this::saveSpecification);
        specificationForm.addListener(SpecificationForm.DeleteEvent.class, this::deleteSpecification);
        specificationForm.addListener(SpecificationForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveSpecification(SpecificationForm.SaveEvent event) {
        specificationService.saveSpecification(event.getSpecification());
        updateList();
        closeEditor();
    }

    private void deleteSpecification(SpecificationForm.DeleteEvent event) {
        specificationService.deleteSpecification(event.getSpecification().getId());
        updateList();
        closeEditor();
    }

    public void editSpecification(Specification specification) {
        if (specification == null) {
            closeEditor();
        } else {
            specificationForm.setSpecification(specification);
            specificationForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        specificationForm.setSpecification(null);
        specificationForm.setVisible(false);
        removeClassName("editing");
    }

    private void addElement() {
        specGrid.asSingleSelect().clear();
        editSpecification(new Specification());
    }


    public void configureGrid(){
        specGrid.addClassName("contact-grid");
        specGrid.setSizeFull();
        specGrid.addColumn(Specification::getName).setHeader("Наименование");
        specGrid.addColumn(spec -> spec.getType().getName()).setHeader("Вид оборудования");
        specGrid.getColumns().forEach(col -> col.setAutoWidth(true));

        specGrid.asSingleSelect().addValueChangeListener(event ->
                editSpecification(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по имени...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

    Button addElementButton = new Button("Добавить характеристику");
        addElementButton.addClickListener(click -> addElement());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addElementButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        specGrid.setItems(specificationService.searchSpecifications(filterText.getValue()));
    }
}
