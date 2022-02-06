package ru.kravchenkoei.stocktaking.ui.views.components.forms;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import ru.kravchenkoei.stocktaking.data.model.Employee;

public class EmployeeForm extends FormLayout {
  private Employee employee;
  TextField firstName = new TextField("Имя");
  TextField secondName = new TextField("Фамилия");
  TextField email = new TextField("Email");
  TextField titleJob = new TextField("Должность");
  TextField phone = new TextField("Телефон");

  Button save = new Button("Сохранить");
  Button delete = new Button("Удалить");
  Button close = new Button("Отменить");

  Binder<Employee> binder = new BeanValidationBinder<>(Employee.class);

  public EmployeeForm() {
    addClassName("contact-form");

    binder.bindInstanceFields(this);
    add(firstName, secondName, email, titleJob, phone, createButtonsLayout());
  }

  private Component createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new EmployeeForm.DeleteEvent(this, employee)));
    close.addClickListener(event -> fireEvent(new EmployeeForm.CloseEvent(this)));

    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
    return new HorizontalLayout(save, delete, close);
  }

  private void validateAndSave() {
    try {
      binder.writeBean(employee);
      fireEvent(new EmployeeForm.SaveEvent(this, employee));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  public void addClickCancelListener(ComponentEventListener<ClickEvent<Button>> clickListener) {
    close.addClickListener(clickListener);
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
    binder.readBean(employee);
  }

  public abstract static class EmployeeFormEvent extends ComponentEvent<EmployeeForm> {
    private Employee employee;

    protected EmployeeFormEvent(EmployeeForm source, Employee employee) {
      super(source, false);
      this.employee = employee;
    }

    public Employee getEmployee() {
      return employee;
    }
  }

  public static class SaveEvent extends EmployeeForm.EmployeeFormEvent {
    SaveEvent(EmployeeForm source, Employee employee) {
      super(source, employee);
    }
  }

  public static class DeleteEvent extends EmployeeForm.EmployeeFormEvent {
    DeleteEvent(EmployeeForm source, Employee employee) {
      super(source, employee);
    }
  }

  public static class CloseEvent extends EmployeeForm.EmployeeFormEvent {
    CloseEvent(EmployeeForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(
      Class<T> eventType, ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }
}
