package org.example.ordersmanager.data.repository;

import org.example.ordersmanager.data.model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
    List<OrderedItem> findAllByOrderId(Long id);

    @Query("SELECT SUM(s.price*s.quantity) FROM OrderedItem s WHERE s.orderId = :orderId")
    BigDecimal getSum(Long orderId);

    Long countOrderedItemsByNameAndOrderId(String itemName, Long orderId);
    OrderedItem findByNameAndOrderId(String itemName, Long orderId);

    @Modifying
    @Query("UPDATE OrderedItem as o SET o.quantity = :newQuantity WHERE o.orderId = :orderId AND o.name = :itemName")
    @Transactional
    void updateOrderedQuantity(Integer newQuantity, Long orderId, String itemName);
}
