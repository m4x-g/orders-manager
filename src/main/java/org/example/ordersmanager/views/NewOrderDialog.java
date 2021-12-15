package org.example.ordersmanager.views;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;

public class NewOrderDialog extends Dialog {

    H1 h1 = new H1("new order!");
    OrderForm orderForm = new OrderForm();

    public NewOrderDialog() {
        this.setCloseOnOutsideClick(true);
        orderForm.setWidth("25em");
        add(h1, orderForm);
    }

    public void showOrderDialog() {
        this.open();
    }
}
