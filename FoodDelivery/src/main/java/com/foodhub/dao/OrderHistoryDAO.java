package com.foodhub.dao;

import java.util.List;

import com.foodhub.model.OrderHistory;

public interface OrderHistoryDAO {
	int insertOrderHistory(OrderHistory oh);  // Insert order history
    List<OrderHistory> fetchOrderHistoryByUserId(int uid);  // Fetch order history by user ID
    int updateOrderHistory(int ohid, String status);  // Update order history by orderHistory ID
}
