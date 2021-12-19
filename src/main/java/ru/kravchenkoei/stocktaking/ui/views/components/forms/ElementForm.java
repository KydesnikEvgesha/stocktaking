package ru.kravchenkoei.stocktaking.ui.views.components.forms;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
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
import ru.kravchenkoei.stocktaking.data.model.Company;
import ru.kravchenkoei.stocktaking.data.model.Element;
import ru.kravchenkoei.stocktaking.data.model.Type;

import java.util.List;

public class ElementForm extends FormLayout {
    private Element element;
    TextField name = new TextField("Наименование");
    ComboBox<Company> company = new ComboBox<>("Производитель");
    ComboBox<Type> type = new ComboBox<>("Вид оборудования");

    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отменить");

    Binder<Element> binder = new BeanValidationBinder<>(Element.class);

    public ElementForm(List<Company> companies, List<Type> types) {
        addClassName("contact-form");
        company.setItemLabelGenerator(Company::getName);
        company.setItems(companies);

        type.setItemLabelGenerator(Type::getName);
        type.setItems(types);

        binder.bindInstanceFields(this);
        add(name,
                company,
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
        delete.addClickListener(event -> fireEvent(new ElementForm.DeleteEvent(this, element)));
        close.addClickListener(event -> fireEvent(new ElementForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(element);
            fireEvent(new ElementForm.SaveEvent(this, element));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setElement(Element element) {
        this.element = element;
        binder.readBean(element);
    }

    public static abstract class ElementFormEvent extends ComponentEvent<ElementForm> {
        private Element element;

        protected ElementFormEvent(ElementForm source, Element element) {
            super(source, false);
            this.element = element;
        }

        public Element getElement() {
            return element;
        }
    }

    public static class SaveEvent extends ElementForm.ElementFormEvent {
        SaveEvent(ElementForm source, Element element) {
            super(source, element);
        }
    }

    public static class DeleteEvent extends ElementForm.ElementFormEvent {
        DeleteEvent(ElementForm source, Element element) {
            super(source, element);
        }

    }

    public static class CloseEvent extends ElementForm.ElementFormEvent {
        CloseEvent(ElementForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
