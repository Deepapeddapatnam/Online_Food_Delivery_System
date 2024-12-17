package com.foodhub.model;

public class Orders {
	private int orderId;
    private int userId;
    private int restaurentId;
    private float totalAmount;
    private String status;
    private java.sql.Timestamp orderDate;
    private String paymentOption;

    // Constructors
    public Orders() {}

    public Orders(int userId, int restaurentId, float totalAmount, String status, String paymentOption) {
        this.userId = userId;
        this.restaurentId = restaurentId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentOption = paymentOption;
    }
    public Orders(int orderId,int userId, int restaurentId, float totalAmount, String status,java.sql.Timestamp timestamp, String paymentOption) {
        this.orderId = orderId;
        this.userId = userId;
        this.restaurentId = restaurentId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate=timestamp;
        this.paymentOption = paymentOption;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurentId() {
        return restaurentId;
    }

    public void setRestaurentId(int restaurentId) {
        this.restaurentId = restaurentId;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(java.sql.Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    @Override
    public String toString() {
        return  orderId +
                "   " + userId +
                "   " + restaurentId +
                "   " + totalAmount +
                "   " + status + 
                "   " + orderDate +
                "   " + paymentOption ;
    }
}
