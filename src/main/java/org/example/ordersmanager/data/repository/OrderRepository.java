package org.example.ordersmanager.data.repository;

import org.example.ordersmanager.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserName(String userName);

    @Modifying
    @Query("UPDATE Order as o SET o.sumTotal = :sumTotal WHERE o.id = :orderId")
    @Transactional
    void updateSum(BigDecimal sumTotal, Long orderId);
}
