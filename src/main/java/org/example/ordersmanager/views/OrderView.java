package org.example.ordersmanager.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.example.ordersmanager.data.model.Order;
import org.example.ordersmanager.data.model.OrderedItem;
import org.example.ordersmanager.data.service.ListService;

import java.util.List;

public class OrderView extends Dialog {
    Grid<OrderedItem> orderedItemGrid = new Grid<>(OrderedItem.class);

    H1 h1 = new H1("not implemented yet 😐");
    Button save = new Button("save", event -> {
        this.close();
    });
    Button delete = new Button("delete", event -> {
        this.close();
    });
    Button close = new Button("close", event -> {
        this.close();
    });

    public OrderView() {
        this.setCloseOnOutsideClick(false);
        configureOrderedItemGrid();
        add(h1, orderedItemGrid, createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

    public void showOrderDetails(Order order) {
        h1.setText("Order ID " + order.getId().toString() + " was clicked!");
        updateOrderedItemGrid(order.getId());
        this.open();
    }

    private void configureOrderedItemGrid() {
        orderedItemGrid.setHeightByRows(true);
        orderedItemGrid.setColumns("orderItem.name", "quantity", "orderItem.price");
        orderedItemGrid.getColumns().forEach(orderedItemColumn -> orderedItemColumn.setAutoWidth(true));
    }

    private void updateOrderedItemGrid(Long id) {
        List<OrderedItem> orderedItemList = ListService.findAllOrderedItems(id);
        orderedItemGrid.setItems(orderedItemList);
    }
}
