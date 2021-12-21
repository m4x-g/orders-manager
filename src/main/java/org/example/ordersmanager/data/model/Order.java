package org.example.ordersmanager.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_status")
    private String status;

    @Column(name = "order_title")
    private String title;

    @Column(name = "order_description")
    private String description;

    @Column(name = "order_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderedItem> orderedItems = new ArrayList<>();

    public Order() {
    }

    public Order(Long id, BigDecimal sumTotal, String status, String title, String description, Date date, User user) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public BigDecimal getTotalPrice() {
        return orderedItems.stream().map(orderedItem -> {
            return orderedItem.getPrice().multiply(new BigDecimal(orderedItem.getQuantity()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPrice2() {
        BigDecimal result = BigDecimal.ZERO;

        for (OrderedItem item : orderedItems) {
            result = result.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
