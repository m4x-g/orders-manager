package org.example.ordersmanager.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.example.ordersmanager.data.model.OrderItem;

import java.util.List;

public class OrderForm extends FormLayout { ;
    TextField description = new TextField("description");
    TextField title = new TextField("title");
    ComboBox<OrderItem> items = new ComboBox<>("items");

    Button save = new Button("save");
    Button close = new Button("close");

    public OrderForm() {
//        this.items.setItems(items);
//        this.items.setItemLabelGenerator(OrderItem::getName);

        add(this.description, this.title, this.items, createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, close);
    }
}
