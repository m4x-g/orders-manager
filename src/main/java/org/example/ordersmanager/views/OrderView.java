package org.example.ordersmanager.views;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.shared.Registration;
import org.example.ordersmanager.auth.CustomUserDetails;
import org.example.ordersmanager.data.model.Order;
import org.example.ordersmanager.data.model.OrderItem;
import org.example.ordersmanager.data.model.OrderedItem;
import org.example.ordersmanager.data.service.ListService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class OrderView extends Dialog {
    Grid<OrderedItem> orderedItemGrid = new Grid<>(OrderedItem.class);
    ComboBox<OrderItem> items = new ComboBox<>("item name");
    NumberField amountOfItems = new NumberField("number of items");
    Button addItemButton = new Button("add");

    CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long orderId;

    H1 h1 = new H1("not implemented yet 😐");
    Button save = new Button("save", event -> this.close());
    Button delete = new Button("delete", event -> this.close());
    Button close = new Button("close", event -> this.close());

    public OrderView() {
        this.setCloseOnOutsideClick(false);
        configureOrderedItemGrid();
        add(h1, orderedItemGrid, addNewItemsLayout(), createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_admin"))) {
            return new HorizontalLayout(save, delete, close);
        } else {
            return new HorizontalLayout(close);
        }
    }

    private HorizontalLayout addNewItemsLayout() {
        amountOfItems.setMin(0);
        addItemButton.addClickListener(event -> newItemsListenerHandler());
        return new HorizontalLayout(items, amountOfItems, addItemButton);
    }

    private void newItemsListenerHandler() {
        if (!items.isEmpty() && !amountOfItems.isEmpty()) {
            OrderedItem orderedItem = new OrderedItem(orderId, items.getValue().getName(), items.getValue().getPrice(), amountOfItems.getValue().intValue());
            fireEvent(new OrderView.SaveEvent(this, orderedItem));
            System.out.println("-->" + items.getValue().getName() + ", " + amountOfItems.getValue());
            amountOfItems.clear();
            items.clear();
            updateOrderedItemGrid(orderId);
        }
    }

    public void showOrderDetails(Order order) {
        h1.setText("Order ID " + order.getId().toString() + " was clicked!");
        orderId = order.getId();
        updateOrderedItemGrid(order.getId());
        this.open();
    }

    private void configureOrderedItemGrid() {
        orderedItemGrid.setHeightByRows(true);
        orderedItemGrid.setColumns("id", "orderId", "name", "price", "quantity");
        orderedItemGrid.getColumns().forEach(orderedItemColumn -> orderedItemColumn.setAutoWidth(true));
    }

    private void updateOrderedItemGrid(Long id) {
        List<OrderedItem> orderedItemList = ListService.findAllOrderedItems(id);
        orderedItemGrid.setItems(orderedItemList);
    }

    // events
    public static abstract class OrderViewEvent extends ComponentEvent<OrderView> {
        OrderedItem orderedItem;

        public OrderViewEvent(OrderView source, OrderedItem orderedItem) {
            super(source, false);
            this.orderedItem = orderedItem;
        }

        public OrderedItem getOrderedItem() {
            return orderedItem;
        }
    }

    public static class SaveEvent extends OrderViewEvent {
        public SaveEvent(OrderView source, OrderedItem orderedItem) {
            super(source, orderedItem);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
