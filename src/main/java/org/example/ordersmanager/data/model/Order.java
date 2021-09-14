package org.example.ordersmanager.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Order {
    @Id
    private Long id;
    private BigDecimal sumTotal;
    private String status;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    public Order() {
    }

    public Order(BigDecimal sumTotal, String status, Date date, User user) {
        this.sumTotal = sumTotal;
        this.status = status;
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

}
