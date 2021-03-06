package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "reciver_name")
    private String reciverName;

    @Column (name = "address")
    private String address;

    @Column (name = "phone")
    private String phone;

    @Column (name = "total_pay")
    private float totalPay;

    @Column (name = "status")
    private boolean status;

    @Column (name = "note")
    private boolean note;

    @Column (name = "order_date")
    private Date orderDate;

    @Column (name = "delivery_date")
    private Date deliveryDate;

    @OneToMany (mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private User user;

    public Order () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getReciverName () {
        return reciverName;
    }

    public void setReciverName (String reciverName) {
        this.reciverName = reciverName;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public float getTotalPay () {
        return totalPay;
    }

    public void setTotalPay (float totalPay) {
        this.totalPay = totalPay;
    }

    public boolean isStatus () {
        return status;
    }

    public void setStatus (boolean status) {
        this.status = status;
    }

    public boolean isNote () {
        return note;
    }

    public void setNote (boolean note) {
        this.note = note;
    }

    public Date getOrderDate () {
        return orderDate;
    }

    public void setOrderDate (Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate () {
        return deliveryDate;
    }

    public void setDeliveryDate (Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Set<OrderDetail> getOrderDetails () {
        return orderDetails;
    }

    public void setOrderDetails (Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public void addOrderDetail (OrderDetail orderDetail) {
        if (orderDetails == null) {
            orderDetails = new HashSet<>();
        }
        orderDetails.add(orderDetail);
    }
}
