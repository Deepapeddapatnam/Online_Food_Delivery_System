<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.foodhub.model.*, com.foodhub.daoimpl.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Summary</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
    }
    .container {
        width: 80%;
        max-width: 800px;
        background-color: #ffffff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        padding: 20px;
        box-sizing: border-box;
        text-align: center;
    }
    .item {
        padding: 15px;
        border-bottom: 1px solid #e0e0e0;
        display: flex;
        justify-content: space-between;
    }
    .item:last-child {
        border-bottom: none;
    }
    .item h3 {
        margin: 0;
        color: #333333;
        font-size: 18px;
    }
    .item p {
        margin: 5px 0;
        color: #666666;
    }
    .total {
        font-size: 20px;
        color: #333333;
        text-align: right;
        padding-top: 10px;
    }
    .back-button {
        margin-top: 20px;
        padding: 10px 20px;
        font-size: 16px;
        color: #ffffff;
        background-color: #F3631B;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
    }
    .back-button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <div class="container">
        <h2 style="text-align:center; color:#333;">Order Summary</h2>
        <% List<OrderItem> items = (List<OrderItem>)session.getAttribute("items"); 
            if(items != null) {
                for(OrderItem item : items) {
                    int menuId = item.getMenuId();
                    Menu menu = new MenuDaoImpl().getMenuById(menuId);
        %>
            <div class="item">
                <div>
                    <h3>Item: <%= menu.getItemName() %></h3>
                    <p>Quantity: <%= item.getQuantity() %></p>
                    <p>Sub Total: ₹<%= item.getSubTotal() %></p>
                </div>
            </div>
        <% 
                } 
            } else { 
        %>
            <p style="text-align:center; color:#888;">Your cart is empty.</p>
        <% } %>

        <!-- Back to Menu Button -->
        <a href="home.jsp" class="back-button">Back</a>
    </div>
</body>
</html>
