package org.example.ordersmanager.views;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import org.example.ordersmanager.auth.CustomUserDetails;
import org.example.ordersmanager.data.model.Order;
import org.example.ordersmanager.data.service.ListService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.Date;

public class NewOrderDialog extends Dialog {
    private Order order;

    TextField description = new TextField("description");
    TextField title = new TextField("title");

    Button save = new Button("save");
    Button close = new Button("close");

    H1 h1 = new H1("new order!");

    FormLayout formLayout = new FormLayout(description, title, createButtonsLayout());

    public NewOrderDialog() {
        add(h1, formLayout);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> saveAndClose());
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, close);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void showOrderDialog() {
        setOrder(new Order());
        this.open();
    }

    private void saveAndClose(){
        order.setDescription(description.getValue());
        order.setTitle(title.getValue());
        order.setDate(new Date());
        order.setStatus("new \uD83D\uDE2E\u200D\uD83D\uDCA8");
        order.setSumTotal(BigDecimal.valueOf(0l));

        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(user.getUser());

        fireEvent(new SaveEvent(this, order));
    }

    // events
    public static abstract class NewOrderDialogEvent extends ComponentEvent<NewOrderDialog> {
        private Order order;

        public NewOrderDialogEvent(NewOrderDialog source, Order order) {
            super(source, false);
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }
    }

    public static class SaveEvent extends NewOrderDialogEvent {
        public SaveEvent(NewOrderDialog source, Order order) {
            super(source, order);
        }
    }

    public static class CloseEvent extends NewOrderDialogEvent {
        public CloseEvent(NewOrderDialog source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
