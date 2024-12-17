package com.foodhub.model;

public class OrderItem {
	private int orderItemId;
    private int orderId;
    private int menuId;
    private int quantity;
    private float subTotal;

    // Constructors
    public OrderItem() {}

    public OrderItem(int orderItemId, int orderId, int menuId, int quantity, float subTotal) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public OrderItem(int orderId, int menuId, int quantity, float subTotal) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return orderItemId + "   " + orderId + "   " + menuId + "   " + quantity + "   " + subTotal;
    }
}
