package com.foodhub.model;

import java.sql.Timestamp;

public class OrderHistory {
	private int orderHistoryId;
    private int userId;
    private int orderId;
    private Timestamp date;
    private float total;
    private String status;

    // Constructors
    public OrderHistory() {}

    public OrderHistory(int userId, int orderId, float total, String status) {
        this.userId = userId;
        this.orderId = orderId;
        this.total = total;
        this.status = status;
    }

    public OrderHistory(int orderHistoryId, int userId, int orderId, Timestamp date, float total, String status) {
        this.orderHistoryId = orderHistoryId;
        this.userId = userId;
        this.orderId = orderId;
        this.date = date;
        this.total = total;
        this.status = status;
    }

    // Getters and Setters
    public int getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return orderHistoryId + " " + userId + " " + orderId + " " + date + " " + total + " " + status;
    }
}
