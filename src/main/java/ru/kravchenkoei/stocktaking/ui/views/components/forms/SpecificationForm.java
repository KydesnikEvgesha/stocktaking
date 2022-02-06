package ru.kravchenkoei.stocktaking.ui.views.components.forms;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import ru.kravchenkoei.stocktaking.data.model.Specification;
import ru.kravchenkoei.stocktaking.data.model.Type;

import java.util.List;

public class SpecificationForm extends FormLayout {
    private Specification specification;
    TextField name = new TextField("Название");
    ComboBox<Type> type = new ComboBox<>("Вид оборудования");

    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отменить");

    Binder<Specification> binder = new BeanValidationBinder<>(Specification.class);

    public SpecificationForm(List<Type> types) {
        addClassName("contact-form");

        type.setItemLabelGenerator(Type::getName);
        type.setItems(types);

        binder.bindInstanceFields(this);
        add(name,
                type,
                createButtonsLayout());
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new SpecificationForm.DeleteEvent(this, specification)));
        close.addClickListener(event -> fireEvent(new SpecificationForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(specification);
            fireEvent(new SpecificationForm.SaveEvent(this, specification));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void addClickCancelListener(ComponentEventListener<ClickEvent<Button>> clickListener){
        close.addClickListener(clickListener);
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
        binder.readBean(specification);
    }

    public static abstract class SpecificationFormEvent extends ComponentEvent<SpecificationForm> {
        private Specification specification;

        protected SpecificationFormEvent(SpecificationForm source, Specification specification) {
            super(source, false);
            this.specification = specification;
        }

        public Specification getSpecification() {
            return specification;
        }
    }

    public static class SaveEvent extends SpecificationFormEvent {
        SaveEvent(SpecificationForm source, Specification specification) {
            super(source, specification);
        }
    }

    public static class DeleteEvent extends SpecificationFormEvent {
        DeleteEvent(SpecificationForm source, Specification specification) {
            super(source, specification);
        }

    }

    public static class CloseEvent extends SpecificationFormEvent {
        CloseEvent(SpecificationForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
