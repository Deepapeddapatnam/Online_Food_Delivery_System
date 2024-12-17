package com.foodhub.dao;

import java.util.List;

import com.foodhub.model.OrderItem;

public interface OrderItemDAO {
    int insertOrderItem(OrderItem oi);  // Insert an order item

    List<OrderItem> fetchOrderItemByOrderId(int oid);  // Fetch order items by orderId
}
