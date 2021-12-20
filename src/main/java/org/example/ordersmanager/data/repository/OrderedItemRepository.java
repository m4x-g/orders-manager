package org.example.ordersmanager.data.repository;

import org.example.ordersmanager.data.model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
    List<OrderedItem> findAllByOrderId(Long id);

    @Query("SELECT SUM(s.price*s.quantity) FROM OrderedItem s WHERE s.orderId = :orderId")
    BigDecimal getSum(Long orderId);
}
