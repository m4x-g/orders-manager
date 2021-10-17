package org.example.ordersmanager.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Route(value = "", layout = MainLayout.class)
@PageTitle("garbage order manager 😐")
public class ListView extends VerticalLayout {
    Grid<Order> orderGrid = new Grid<>(Order.class);
    Grid<OrderItem> orderItemGrid = new Grid<>(OrderItem.class);
    Grid<OrderedItem> orderedItemGrid = new Grid<>(OrderedItem.class);
    OrderView orderView = new OrderView();
    TextField filterText = new TextField();
    ListService listService;

    public ListView(ListService listService) {
        this.listService = listService;
        setSizeFull();
        configureOrderGrid();
        orderView.setSizeFull();

        add(getToolbar(), getContent(), paragraph());
        updateList();

//        configureOrderItemGrid();
//        add(orderItemGrid);
//        updateOrderItemGrid();

//        configureOrderedItemGrid();
//        add(orderedItemGrid);
//        updateOrderedItemGrid();
    }

    private void configureOrderGrid() {
        orderGrid.setSizeFull();
        orderGrid.setColumns("id", "sumTotal", "status", "date");
        orderGrid.addColumn(order -> order.getUser().getName()).setHeader("user name");
        orderGrid.addColumn(order -> order.getUser().getAddress()).setHeader("user address");
        orderGrid.getColumns().forEach(orderColumn -> orderColumn.setAutoWidth(true));

//        orderGrid.asSingleSelect().addValueChangeListener(event -> getOrderInfo(event.getValue()));
        orderGrid.asSingleSelect().addValueChangeListener(event -> orderView.showOrderDetails(event.getValue()));
    }

    private void getOrderInfo(Order order) {
        System.out.println(order.getUser().getName());
    }

    private void toggleOrderView() {
        if (orderView.isVisible()) {
            orderView.setVisible(false);
        } else {
            orderView.setVisible(true);
        }

    }
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(orderGrid);
        content.setSizeFull();
        return content;
    }

    private void configureOrderItemGrid() {
        orderItemGrid.setSizeFull();
        orderItemGrid.setColumns("id", "name", "price");
        orderItemGrid.getColumns().forEach(orderItemColumn -> orderItemColumn.setAutoWidth(true));
    }

    private void configureOrderedItemGrid() {
        orderedItemGrid.setSizeFull();
//        orderedItemGrid.removeAllColumns();
//        orderedItemGrid.addColumn(orderedItem -> orderedItem.getOrderItem().getName()).setHeader("ordered item name");
        orderedItemGrid.setColumns("orderItem.name", "quantity", "orderId");
//        orderedItemGrid.addColumn(orderedItem -> orderedItem.getQuantity()).setHeader("quantity");
//        orderedItemGrid.addColumn(orderedItem -> orderedItem.getOrderId()).setHeader("orderId");
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
//        List<OrderedItem> orderedItemList = listService.findAllOrderedItems();
        List<OrderedItem> orderedItemList = listService.findAllOrderedItems(null);
        orderedItemGrid.setItems(orderedItemList);
    }

    @Secured("ROLE_ADMIN")
    private Paragraph paragraph() {
        return new Paragraph("wizards only, fool!");
    }
}
