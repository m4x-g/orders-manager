package org.example.ordersmanager.data.service;

import org.example.ordersmanager.data.model.Order;
import org.example.ordersmanager.data.model.User;
import org.example.ordersmanager.data.repository.OrderRepository;
import org.example.ordersmanager.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ListService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public ListService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return userRepository.findAll();
        } else {
            //implement filter later
            return userRepository.findAll();
        }
    }

    public long countUsers() {
        return userRepository.count();
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void saveUser(User user) {
        if (user == null) {
            System.err.println("user is null");
            return;
        }
        userRepository.save(user);
    }

    public List<Order> findAllOrders() {
//        return orderRepository.findAll();

        User johnDoe = new User("John Doe", "password", "nowhere", "user");
        User janeDoe = new User("Jane Doe", "password", "somewhere", "operator");
        User orgalorg = new User("orgalorg", "password", "everywhere", "admin");

//        OrderItem itemOne = new OrderItem(101, "item one", new BigDecimal("9.95"), 3);
//        OrderItem itemTwo = new OrderItem(102, "item two", new BigDecimal("19.95"), 7);
//        OrderItem itemThree = new OrderItem(103, "item three", new BigDecimal("5.00"), 1);
//        OrderItem itemFour = new OrderItem(104, "item four", new BigDecimal("17.55"), 2);
//        OrderItem itemFive = new OrderItem(105, "item five", new BigDecimal("99.95"), 5);
//
//        List<OrderItem> orderOneItems = new ArrayList<>();
//        orderOneItems.add(itemOne);
//        orderOneItems.add(itemThree);
//        List<OrderItem> orderTwoItems = new ArrayList<>();
//        orderTwoItems.add(itemFour);
//        orderTwoItems.add(itemFive);
//        orderTwoItems.add(itemOne);
//        List<OrderItem> orderThreeItems = new ArrayList<>();
//        orderThreeItems.add(itemTwo);
//        orderThreeItems.add(itemFour);
//        List<OrderItem> orderFourItems = new ArrayList<>();
//        orderFourItems.add(itemOne);
//        orderFourItems.add(itemThree);
//        orderFourItems.add(itemFive);

        Order orderOne = new Order(new BigDecimal("19.95"), "pending", new Date(), janeDoe);
        Order orderTwo =  new Order(new BigDecimal("59.95"), "canceled", new Date(), janeDoe);
        Order orderThree =  new Order(new BigDecimal("39.00"), "delivered", new Date(), johnDoe);
        Order orderFour =  new Order(new BigDecimal("125.75"), "pending", new Date(), orgalorg);

        List<Order> orders = new ArrayList<>();
        orders.add(orderOne);
        orders.add(orderTwo);
        orders.add(orderThree);
        orders.add(orderFour);
        return orders;
    }
}
