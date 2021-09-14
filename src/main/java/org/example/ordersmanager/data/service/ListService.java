package org.example.ordersmanager.data.service;

import org.example.ordersmanager.data.model.Order;
import org.example.ordersmanager.data.model.User;
import org.example.ordersmanager.data.repository.OrderRepository;
import org.example.ordersmanager.data.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public List<Order> findAllOrders(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return orderRepository.findAll();
        } else {
            //implement filter later
            return orderRepository.findAll();
        }
    }
}
