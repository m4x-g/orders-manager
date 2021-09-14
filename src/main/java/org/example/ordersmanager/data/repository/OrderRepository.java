package org.example.ordersmanager.data.repository;

import org.example.ordersmanager.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
