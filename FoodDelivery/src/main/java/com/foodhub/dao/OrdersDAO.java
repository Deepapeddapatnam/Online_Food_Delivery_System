package com.foodhub.dao;

import java.util.List;

import com.foodhub.model.Orders;

public interface OrdersDAO {
	Orders fetchOrdersByOrderId(int id);  // Fetch order by orderId

    int insertOrderDetails(Orders order); // Insert order details

    int updateOrderStatusById(int id, String status);  // Update order status by orderId

    List<Orders> fetchOrdersByUserId(int uid);  // Fetch orders by userId
    
    int getMaxOrderId();
}
