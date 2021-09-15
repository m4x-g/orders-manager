package org.example.ordersmanager.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.example.ordersmanager.data.model.Order;
import org.example.ordersmanager.data.model.OrderItem;
import org.example.ordersmanager.data.model.OrderedItem;
import org.example.ordersmanager.data.service.ListService;

@Route(value = "")
@PageTitle("garbage order manager 😐")
public class ListView extends VerticalLayout {
    Grid<Order> orderGrid = new Grid<>(Order.class);
    Grid<OrderItem> orderItemGrid = new Grid<>(OrderItem.class);
    Grid<OrderedItem> orderedItemGrid = new Grid<>(OrderedItem.class);
    TextField filterText = new TextField();
    ListService listService;

    public ListView(ListService listService) {
        this.listService = listService;
        setSizeFull();
        configureOrderGrid();
        configureOrderItemGrid();
        configureOrderedItemGrid();
        add(getToolbar(), orderGrid, orderItemGrid, orderedItemGrid);
        updateList();
        updateOrderItemGrid();
        updateOrderedItemGrid();
    }

    private void configureOrderGrid() {
        orderGrid.setSizeFull();
        orderGrid.setColumns("id", "sumTotal", "status", "date");
        orderGrid.addColumn(order -> order.getUser().getName()).setHeader("user name");
        orderGrid.addColumn(order -> order.getUser().getAddress()).setHeader("user address");
        orderGrid.getColumns().forEach(orderColumn -> orderColumn.setAutoWidth(true));
    }

    private void configureOrderItemGrid() {
        orderItemGrid.setSizeFull();
        orderItemGrid.setColumns("id", "name", "price");
        orderItemGrid.getColumns().forEach(orderItemColumn -> orderItemColumn.setAutoWidth(true));
    }

    private void configureOrderedItemGrid() {
        orderedItemGrid.setSizeFull();
        orderedItemGrid.setColumns("orderId", "quantity");
        orderedItemGrid.addColumn(orderedItem -> orderedItem.getClass().getName()).setHeader("order item");
        orderedItemGrid.getColumns().forEach(orderedItemColumn -> orderedItemColumn.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(event -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        return toolbar;
    }

    private void updateList() {
        orderGrid.setItems(listService.findAllOrders(filterText.getValue()));
    }

    private void updateOrderItemGrid() {
        orderItemGrid.setItems(listService.findAllItems());
    }

    private void updateOrderedItemGrid() {
        orderedItemGrid.setItems(listService.findAllOrderedItems());
    }
}
