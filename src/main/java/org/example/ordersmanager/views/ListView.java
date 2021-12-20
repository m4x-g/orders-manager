package org.example.ordersmanager.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.example.ordersmanager.data.model.Order;
import org.example.ordersmanager.data.model.OrderItem;
import org.example.ordersmanager.data.service.ListService;
import org.springframework.security.access.annotation.Secured;

@Route(value = "", layout = MainLayout.class)
@PageTitle("garbage order manager 😐")
@Secured("ROLE_ADMIN")
public class ListView extends VerticalLayout {
    Grid<Order> orderGrid = new Grid<>(Order.class);
    OrderView orderView = new OrderView();
    TextField filterText = new TextField();
    NewOrderDialog newOrderDialog = new NewOrderDialog();
    Button newOrderButton = new Button("new order");
    ListService listService;

    public ListView(ListService listService) {
        this.listService = listService;
        setSizeFull();
        configureOrderGrid();
        orderView.items.setItems(listService.findAllItems());
        orderView.items.setItemLabelGenerator(OrderItem::getName);
        orderView.addListener(OrderView.SaveEvent.class, this::saveNewOrderItem);
        orderView.addListener(OrderView.CloseEvent.class, this::closeOrderView);
        orderView.setSizeFull();

        add(getToolbar(), getContent(), paragraph());
        updateList();
    }

    private void configureOrderGrid() {
        orderGrid.setSizeFull();
        orderGrid.setColumns("id", "sumTotal", "status", "title", "description", "date");
        orderGrid.addColumn(order -> order.getUser().getName()).setHeader("user name");
        orderGrid.addColumn(order -> order.getUser().getAddress()).setHeader("user address");
        orderGrid.getColumns().forEach(orderColumn -> orderColumn.setAutoWidth(true));

//        orderGrid.asSingleSelect().addValueChangeListener(event -> getOrderInfo(event.getValue()));
        orderGrid.asSingleSelect().addValueChangeListener(event -> orderView.showOrderDetails(event.getValue()));
    }

//    private void getOrderInfo(Order value) {
//        System.out.println(" --> " + value.getUser().getName());
//    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(orderGrid);
        content.setSizeFull();
        return content;
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(event -> updateList());

        newOrderDialog.addListener(NewOrderDialog.SaveEvent.class, this::saveOrder);
        newOrderDialog.addListener(NewOrderDialog.CloseEvent.class, this::closeNewOrder);

        newOrderButton.addClickListener(buttonClickEvent -> newOrderDialog.showOrderDialog());

        return new HorizontalLayout(filterText, newOrderButton);
    }

    private void updateList() {
        orderGrid.setItems(listService.findAllOrders(filterText.getValue()));
    }

    private void saveOrder(NewOrderDialog.SaveEvent event) {
        System.out.println("save event was fired from the new order dialog window!");
        System.out.println(" ---> " + event.getOrder().toString());
        listService.saveOrder(event.getOrder());
        newOrderDialog.close();
        newOrderDialog.setOrder(null);
        newOrderDialog.description.clear();
        newOrderDialog.title.clear();
        updateList();
    }

    private void saveNewOrderItem(OrderView.SaveEvent event) {
        System.out.println("save event was fired from order view!");
        listService.saveNewOrderedItem(event.getOrderedItem());
        listService.updateSum(listService.getSum(event.orderedItem.getOrderId()), event.orderedItem.getOrderId());
        System.out.println(listService.getSum(event.getOrderedItem().getOrderId()).toString());
    }

    private void closeNewOrder(NewOrderDialog.CloseEvent event) {
        System.out.println("close event vas fired from the new order dialog window!");
        newOrderDialog.close();
    }

    private void closeOrderView(OrderView.CloseEvent event) {
        System.out.println("close event was fired from OrderView!");
        orderView.close();
        updateList();
    }

    @Secured("ROLE_ADMIN")
    private Paragraph paragraph() {
        return new Paragraph(listService.getSum(1l).toString());
    }
}
