package org.example.ordersmanager.data.repository;

import org.example.ordersmanager.data.model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
}
