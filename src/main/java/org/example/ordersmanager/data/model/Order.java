package org.example.ordersmanager.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_sum_total")
    private BigDecimal sumTotal;

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

    public Order() {
    }

    public Order(Long id, BigDecimal sumTotal, String status, String title, String description, Date date, User user) {
        this.id = id;
        this.sumTotal = sumTotal;
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

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(BigDecimal sumTotal) {
        this.sumTotal = sumTotal;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", sumTotal=" + sumTotal +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
