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
import ru.kravchenkoei.stocktaking.data.model.Location;

public class LocationForm extends FormLayout {
  private Location location;
  TextField nameOffice = new TextField("Название площадки");
  TextField address = new TextField("Адрес");

  Button save = new Button("Сохранить");
  Button delete = new Button("Удалить");
  Button close = new Button("Отменить");

  Binder<Location> binder = new BeanValidationBinder<>(Location.class);

  public LocationForm() {
    addClassName("contact-form");

    binder.bindInstanceFields(this);
    add(nameOffice, address, createButtonsLayout());
  }

  private Component createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new LocationForm.DeleteEvent(this, location)));
    close.addClickListener(event -> fireEvent(new LocationForm.CloseEvent(this)));

    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
    return new HorizontalLayout(save, delete, close);
  }

  private void validateAndSave() {
    try {
      binder.writeBean(location);
      fireEvent(new LocationForm.SaveEvent(this, location));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  public void addClickCancelListener(ComponentEventListener<ClickEvent<Button>> clickListener) {
    close.addClickListener(clickListener);
  }

  public void setLocation(Location location) {
    this.location = location;
    binder.readBean(location);
  }

  public abstract static class LocationFormEvent extends ComponentEvent<LocationForm> {
    private Location location;

    protected LocationFormEvent(LocationForm source, Location location) {
      super(source, false);
      this.location = location;
    }

    public Location getLocation() {
      return location;
    }
  }

  public static class SaveEvent extends LocationForm.LocationFormEvent {
    SaveEvent(LocationForm source, Location location) {
      super(source, location);
    }
  }

  public static class DeleteEvent extends LocationForm.LocationFormEvent {
    DeleteEvent(LocationForm source, Location location) {
      super(source, location);
    }
  }

  public static class CloseEvent extends LocationForm.LocationFormEvent {
    CloseEvent(LocationForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(
      Class<T> eventType, ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }
}
