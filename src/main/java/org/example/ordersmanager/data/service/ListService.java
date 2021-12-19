package org.example.ordersmanager.data.service;

import org.example.ordersmanager.auth.CustomUserDetails;
import org.example.ordersmanager.data.model.Order;
import org.example.ordersmanager.data.model.OrderItem;
import org.example.ordersmanager.data.model.OrderedItem;
import org.example.ordersmanager.data.model.User;
import org.example.ordersmanager.data.repository.OrderItemRepository;
import org.example.ordersmanager.data.repository.OrderRepository;
import org.example.ordersmanager.data.repository.OrderedItemRepository;
import org.example.ordersmanager.data.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private static OrderedItemRepository orderedItemRepository;

    public ListService(OrderRepository orderRepository, UserRepository userRepository,
                       OrderItemRepository orderItemRepository, OrderedItemRepository orderedItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderedItemRepository = orderedItemRepository;
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

    public List<Order> findAllOrders(String stringFilter) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_admin")) ||
                user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_operator"))) {
            if (stringFilter == null || stringFilter.isEmpty()) {
                return orderRepository.findAll();
            } else {
                //implement filter later
                return orderRepository.findAll();
            }
        } else {
            return orderRepository.findAllByUserName(user.getUsername());
        }
    }

    public List<OrderItem> findAllItems() {
        return orderItemRepository.findAll();
    }

    public static List<OrderedItem> findAllOrderedItems(Long id) {
        if (id == null) {
            return orderedItemRepository.findAll();
        } else {
            return orderedItemRepository.findAllByOrderId(id);
        }
    }

    public void saveOrder(Order order) {
        if (order == null) {
            System.err.println("order is null");
            return;
        }
        orderRepository.save(order);
    }

    public void saveNewOrderedItem(OrderedItem orderedItem) {
        if (orderedItem == null) {
            System.err.println("ordered item is null!");
            return;
        }
        orderedItemRepository.save(orderedItem);
    }
}
