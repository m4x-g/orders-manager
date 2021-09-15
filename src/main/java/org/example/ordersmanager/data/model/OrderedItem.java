package org.example.ordersmanager.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ordered_items")
public class OrderedItem {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToMany
    @JoinColumn(name = "item_id")
    private List<OrderItem> orderItem;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "quantity")
    private int quantity;

    public OrderedItem() {
    }

    public OrderedItem(Long id, List<OrderItem> orderItem, Long orderId, int quantity) {
        this.id = id;
        this.orderItem = orderItem;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
