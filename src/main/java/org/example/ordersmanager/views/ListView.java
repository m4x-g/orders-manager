package org.example.ordersmanager.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.example.ordersmanager.data.model.Order;
import org.example.ordersmanager.data.model.OrderItem;
import org.example.ordersmanager.data.model.OrderedItem;
import org.example.ordersmanager.data.service.ListService;
import org.springframework.security.access.annotation.Secured;

import java.util.Locale;

@Route(value = "", layout = MainLayout.class)
@PageTitle("garbage order manager 😐")
@Secured("ROLE_ADMIN")
public class ListView extends VerticalLayout {
    Grid<Order> orderGrid = new Grid<>(Order.class);
    OrderView orderView = new OrderView();
    TextField filterText = new TextField();
    NewOrderDialog newOrderDialog = new NewOrderDialog();
//    Button newOrderButton = new Button("new order");
    Button newOrderButton = new Button(getTranslation("btn.new-order"));
    Button changeLocale = new Button("change language");

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
        orderGrid.removeAllColumns();
//        orderGrid.setColumns("id", "sumTotal", "status", "title", "description", "date");
        orderGrid.addColumn(Order::getId).setHeader("id");
        orderGrid.addColumn(Order::getTotalPrice).setHeader("price 1");
        orderGrid.addColumn(Order::getStatus).setHeader("status");
        orderGrid.addColumn(Order::getTitle).setHeader("title");
        orderGrid.addColumn(Order::getDescription).setHeader("description");
        orderGrid.addColumn(Order::getDate).setHeader("date");
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

        changeLocale.addClickListener(buttonClickEvent -> changeLanguage());

        return new HorizontalLayout(filterText, newOrderButton, changeLocale);
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
        Long orderId = event.getOrderedItem().getOrderId();
        String itemName = event.getOrderedItem().getName();
        System.out.println(" -- save newOrder Items count -->" + listService.countOrderedItemsByNameAndOrderId(itemName, orderId));
        if (listService.countOrderedItemsByNameAndOrderId(itemName, orderId) > 0) {
            // update the amount of the already existing item
            OrderedItem existingItem = listService.findByNameAndOrderId(itemName, orderId);
            OrderedItem newItem = event.getOrderedItem();

            Integer oldQuantity = existingItem.getQuantity();
            Integer newQuantity = oldQuantity + newItem.getQuantity();

            listService.updateOrderedQuantity(newQuantity, orderId, itemName);
        } else {
            // insert the new item in to the table
            listService.saveNewOrderedItem(event.getOrderedItem());
        }
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

    private void changeLanguage() {
        if (VaadinSession.getCurrent().getLocale().equals(Locale.ENGLISH)) {
            VaadinSession.getCurrent().setLocale(Locale.GERMAN);
            showLocaleChangeNotification();
        } else {
            VaadinSession.getCurrent().setLocale(Locale.ENGLISH);
            showLocaleChangeNotification();
        }
    }

    private void showLocaleChangeNotification() {
        Notification notification = new Notification("locale set to " + VaadinSession.getCurrent().getLocale().getDisplayLanguage(), 1000, Notification.Position.TOP_CENTER);
        notification.open();
    }

    @Secured("ROLE_ADMIN")
    private Paragraph paragraph() {
        return new Paragraph(listService.getSum(1l).toString());
    }
}
