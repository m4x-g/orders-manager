package org.example.ordersmanager.data.repository;

import org.example.ordersmanager.data.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository <OrderItem, Long> {
}
