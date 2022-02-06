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
import ru.kravchenkoei.stocktaking.data.model.Company;

public class CompanyForm extends FormLayout {
  private Company company;
  TextField name = new TextField("Название");
  TextField address = new TextField("Адрес");

  Button save = new Button("Сохранить");
  Button delete = new Button("Удалить");
  Button close = new Button("Отменить");

  Binder<Company> binder = new BeanValidationBinder<>(Company.class);

  public CompanyForm() {
    addClassName("contact-form");

    binder.bindInstanceFields(this);
    add(name, address, createButtonsLayout());
  }

  private Component createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, company)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));

    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
    return new HorizontalLayout(save, delete, close);
  }

  private void validateAndSave() {
    try {
      binder.writeBean(company);
      fireEvent(new SaveEvent(this, company));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  public void addClickCancelListener(ComponentEventListener<ClickEvent<Button>> clickListener) {
    close.addClickListener(clickListener);
  }

  public void setCompany(Company company) {
    this.company = company;
    binder.readBean(company);
  }

  public abstract static class CompanyFormEvent extends ComponentEvent<CompanyForm> {
    private Company company;

    protected CompanyFormEvent(CompanyForm source, Company company) {
      super(source, false);
      this.company = company;
    }

    public Company getCompany() {
      return company;
    }
  }

  public static class SaveEvent extends CompanyFormEvent {
    SaveEvent(CompanyForm source, Company company) {
      super(source, company);
    }
  }

  public static class DeleteEvent extends CompanyFormEvent {
    DeleteEvent(CompanyForm source, Company company) {
      super(source, company);
    }
  }

  public static class CloseEvent extends CompanyFormEvent {
    CloseEvent(CompanyForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(
      Class<T> eventType, ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }
}
