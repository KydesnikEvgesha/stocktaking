package ru.kravchenkoei.stocktaking.ui.views.components.forms;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import ru.kravchenkoei.stocktaking.data.model.Type;

public class TypeForm extends FormLayout {
    private Type type;
    TextField name = new TextField("Наименование");

    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отменить");

    Binder<Type> binder = new BeanValidationBinder<>(Type.class);

    public TypeForm() {
        addClassName("contact-form");

        binder.bindInstanceFields(this);
        add(name,
                createButtonsLayout());
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new TypeForm.DeleteEvent(this, type)));
        close.addClickListener(event -> fireEvent(new TypeForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(type);
            fireEvent(new TypeForm.SaveEvent(this, type));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setType(Type type) {
        this.type = type;
        binder.readBean(type);
    }

    public static abstract class TypeFormEvent extends ComponentEvent<TypeForm> {
        private Type type;

        protected TypeFormEvent(TypeForm source, Type type) {
            super(source, false);
            this.type = type;
        }

        public Type getType() {
            return type;
        }
    }

    public static class SaveEvent extends TypeForm.TypeFormEvent {
        SaveEvent(TypeForm source, Type type) {
            super(source, type);
        }
    }

    public static class DeleteEvent extends TypeForm.TypeFormEvent {
        DeleteEvent(TypeForm source, Type type) {
            super(source, type);
        }

    }

    public static class CloseEvent extends TypeForm.TypeFormEvent {
        CloseEvent(TypeForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
