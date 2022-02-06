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
import ru.kravchenkoei.stocktaking.data.model.Employee;
import ru.kravchenkoei.stocktaking.data.service.EmployeeService;
import ru.kravchenkoei.stocktaking.ui.views.MainLayout;
import ru.kravchenkoei.stocktaking.ui.views.components.forms.EmployeeForm;

@PageTitle("Сотрудники")
@Route(value = "/employee", layout = MainLayout.class)
public class EmployeeListView extends VerticalLayout {
    Grid<Employee> employeeGrid = new Grid<>();
    TextField filterText = new TextField();
    EmployeeForm employeeForm;
    EmployeeService employeeService;

    public EmployeeListView(EmployeeService employeeService) {
        this.employeeService = employeeService;

        setSpacing(false);
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(employeeGrid, employeeForm);
        content.setFlexGrow(2, employeeGrid);
        content.setFlexGrow(1, employeeForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        employeeForm = new EmployeeForm();
        employeeForm.setWidth("25em");
        employeeForm.addListener(EmployeeForm.SaveEvent.class, this::saveEmployee);
        employeeForm.addListener(EmployeeForm.DeleteEvent.class, this::deleteEmployee);
        employeeForm.addListener(EmployeeForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveEmployee(EmployeeForm.SaveEvent event) {
        employeeService.saveEmployee(event.getEmployee());
        updateList();
        closeEditor();
    }

    private void deleteEmployee(EmployeeForm.DeleteEvent event) {
        employeeService.deleteEmployee(event.getEmployee().getId());
        updateList();
        closeEditor();
    }

    public void editEmployee(Employee employee) {
        if (employee == null) {
            closeEditor();
        } else {
            employeeForm.setEmployee(employee);
            employeeForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        employeeForm.setEmployee(null);
        employeeForm.setVisible(false);
        removeClassName("editing");
    }

    private void addEmployee() {
        employeeGrid.asSingleSelect().clear();
        editEmployee(new Employee());
    }


    public void configureGrid(){
        employeeGrid.addClassName("contact-grid");
        employeeGrid.setSizeFull();
        employeeGrid.addColumn(Employee::getFirstName).setHeader("Имя");
        employeeGrid.addColumn(Employee::getSecondName).setHeader("Фамилия");
        employeeGrid.addColumn(Employee::getEmail).setHeader("Email");
        employeeGrid.addColumn(Employee::getTitleJob).setHeader("Должность");
        employeeGrid.addColumn(Employee::getPhone).setHeader("Телефон");
        employeeGrid.getColumns().forEach(col -> col.setAutoWidth(true));

        employeeGrid.asSingleSelect().addValueChangeListener(event ->
                editEmployee(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по имени...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addEmployeeButton = new Button("Добавить сотрудника");
        addEmployeeButton.addClickListener(click -> addEmployee());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addEmployeeButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        employeeGrid.setItems(employeeService.searchEmployees(filterText.getValue()));
    }
}
