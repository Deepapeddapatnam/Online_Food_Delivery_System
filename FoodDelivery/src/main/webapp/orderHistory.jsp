<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.foodhub.model.OrderHistory" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order History</title>
<style>
    /* General reset and body styling */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f9f9f9;
        padding: 20px;
        color: #333;
    }

    /* Home button styling */
    .home-btn {
        display: inline-block;
        padding: 10px 20px;
        color: #F3631B;
        background-color: #ECD5C2;
        text-decoration: none;
        font-weight: bold;
        border-radius: 5px;
        transition: background 0.3s ease;
        margin-bottom: 20px;
    }

    .home-btn:hover {
        background-color: #f1ab7c;
    }

    /* Title styling */
    h1 {
        text-align: center;
        margin-bottom: 30px;
        color: #F3631B;
    }

    /* Order card styling */
    .order-card {
        background-color: #fff;
        margin: 20px auto;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 80%;
        max-width: 600px;
    }

    .order-title {
        font-size: 18px;
        font-weight: bold;
        color: #333;
        margin-bottom: 10px;
    }

    .order-details {
        margin: 10px 0;
    }

    .order-details span {
        font-weight: bold;
        color: #555;
    }

    /* View details button styling */
    .view-details-btn {
        background-color: #F3631B;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 14px;
        transition: background 0.3s ease;
    }

    .view-details-btn:hover {
        background-color: #d9531b;
    }

    /* No history message styling */
    .no-history {
        text-align: center;
        font-size: 16px;
        color: #666;
        margin-top: 50px;
    }

</style>
</head>
<body>
<a href="home.jsp" class="home-btn">Home Page</a>

<h1>Order History</h1>

<% List<OrderHistory> orderHistory = (List<OrderHistory>) session.getAttribute("orderHistory"); 
    if (orderHistory != null && !orderHistory.isEmpty()) {
        for (OrderHistory oh : orderHistory) {
%>
    <div class="order-card">
        <div class="order-title">Order ID: <%= oh.getOrderId() %></div>
        <p class="order-details"><span>Order Date:</span> <%= oh.getDate() %></p>
        <p class="order-details"><span>Order Status:</span> <%= oh.getStatus() %></p>
        <p class="order-details"><span>Total Amount:</span> ₹<%= oh.getTotal() %></p>
        <button class="view-details-btn" onclick="location.href='OrderItems?orderId=<%= oh.getOrderId() %>'">View Details</button>
    </div>
<% 
        } 
    } else { 
%>
    <p class="no-history">No order history available.</p>
<% 
    } 
%>
</body>
</html>
