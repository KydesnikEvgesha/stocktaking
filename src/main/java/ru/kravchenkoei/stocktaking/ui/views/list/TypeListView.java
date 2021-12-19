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
import ru.kravchenkoei.stocktaking.data.model.Type;
import ru.kravchenkoei.stocktaking.data.service.TypeService;
import ru.kravchenkoei.stocktaking.ui.views.MainLayout;
import ru.kravchenkoei.stocktaking.ui.views.components.forms.TypeForm;

@PageTitle("Вид оборудования")
@Route(value = "/type", layout = MainLayout.class)
public class TypeListView extends VerticalLayout {
    Grid<Type> typesGrid = new Grid<>();
    TextField filterText = new TextField();
    TypeForm typeForm;
    TypeService typeService;

    public TypeListView(TypeService typeService) {
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
        HorizontalLayout content = new HorizontalLayout(typesGrid, typeForm);
        content.setFlexGrow(2, typesGrid);
        content.setFlexGrow(1, typeForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        typeForm = new TypeForm();
        typeForm.setWidth("25em");
        typeForm.addListener(TypeForm.SaveEvent.class, this::saveType);
        typeForm.addListener(TypeForm.DeleteEvent.class, this::deleteType);
        typeForm.addListener(TypeForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveType(TypeForm.SaveEvent event) {
        typeService.saveType(event.getType());
        updateList();
        closeEditor();
    }

    private void deleteType(TypeForm.DeleteEvent event) {
        typeService.deleteType(event.getType().getId());
        updateList();
        closeEditor();
    }

    public void editType(Type type) {
        if (type == null) {
            closeEditor();
        } else {
            typeForm.setType(type);
            typeForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        typeForm.setType(null);
        typeForm.setVisible(false);
        removeClassName("editing");
    }

    private void addType() {
        typesGrid.asSingleSelect().clear();
        editType(new Type());
    }


    public void configureGrid(){
        typesGrid.addClassName("contact-grid");
        typesGrid.setSizeFull();
        typesGrid.addColumn(Type::getName).setHeader("Наименование");
        typesGrid.getColumns().forEach(col -> col.setAutoWidth(true));

        typesGrid.asSingleSelect().addValueChangeListener(event ->
                editType(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по имени...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addElementButton = new Button("Добавить продукцию");
        addElementButton.addClickListener(click -> addType());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addElementButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        typesGrid.setItems(typeService.searchTypes(filterText.getValue()));
    }
}
