<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="java.util.Map.Entry" %>
<%@ page import ="java.util.*" %>
<%@ page import ="com.foodhub.model.Cart" %> 
<%@ page import ="com.foodhub.model.CartItem" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart Items</title>
    <style>
        /* General styling */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f9;
        }

        /* Navbar styling */
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: white;
            padding: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        .navbar h1 {
            margin: 0;
            font-size: 1.5em;
            flex-grow: 1;
            text-align: center;
            color: #333;
        }

        /* Navbar buttons */
        .navbar button {
            background-color: white;
            color: #F3631B;
            font-weight : bold;
            border: none;
            padding: 8px 16px;
            font-size: 1em;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .navbar button:hover {
            background-color: #e2e6ea;
        }

        /* Cart container */
        .menu-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: space-between;
            margin-top: 20px;
        }

        /* Individual cart item box */
        .menu-item-box {
            flex: 0 0 calc(33.33% - 20px);
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #fff;
            padding: 15px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        /* Hover effect */
        .menu-item-box:hover {
            transform: scale(1.05);
            box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.2);
        }

        /* Item details */
        .item-name {
            font-size: 1.2em;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }

        .menu-details {
            display: flex;
            width: 100%;
            justify-content: space-between;
            font-size: 0.9em;
            color: #555;
        }

        /* Action buttons */
        .menu-buttons {
            display: flex;
            justify-content: space-between;
            width: 100%;
            margin-top: 10px;
        }

        .menu-buttons button {
            padding: 6px 12px;
            font-size: 0.9em;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .menu-buttons .update {
            background-color: #388e3c;
        }

        .menu-buttons .delete {
            background-color: #dc3545;
        }

        .menu-buttons .update:hover {
            background-color: #0056b3;
        }

        .menu-buttons .delete:hover {
            background-color: #c82333;
        }

        /* Quantity input field */
        input[type="number"] {
            width: 50px;
            padding: 2px;
            text-align: center;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-right: 10px;
        }

        /* Empty cart message */
        .empty-cart-message {
            text-align: center;
            margin-top: 50px;
            font-size: 1.5em;
            color: #555;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<div class="navbar">
    <button onclick="window.history.back()">Back</button>
    <h1>Welcome to the Cart</h1>
    <button onclick="window.location.href='checkout.jsp'">Proceed to Pay</button>
    <button onclick="window.location.href='home.jsp'">Home</button>
    <form action="AddToCartServlet" method="post" style="display:inline;">
        <button class="delete" type="submit">Clear Cart</button>
        <input type="hidden" name="action" value="clear"> 
    </form>
</div>

<% 
    Cart cart = (Cart)session.getAttribute("cart");
    if (cart == null || cart.getItems().isEmpty()) {
%>
    <!-- Empty cart message -->
    <div class="empty-cart-message">
        Your cart is empty.
    </div>
<% 
    } else {
        Map<Integer, CartItem> items = cart.getItems();
        Set<Entry<Integer, CartItem>> itemset = items.entrySet();
%>
    <div class="menu-container">
    <% for (Entry<Integer, CartItem> item : itemset) { %>
        <div class="menu-item-box">
            <!-- Item Name -->
            <div class="item-name">Name: <%= item.getValue().getName() %></div>

            <!-- Quantity, Price, Subtotal -->
            <div class="menu-details">
                <div>Quantity: <%= item.getValue().getQuantity() %></div>
                <div>Price: ₹<%= item.getValue().getPrice() %></div>
                <div>Subtotal: ₹<%= item.getValue().getSubTotal() %></div>
            </div>

            <!-- Update and Delete Buttons -->
            <div class="menu-buttons">
                <form action="AddToCartServlet?menuId=<%= item.getValue().getItemId() %>" method="post" style="display: inline;">
                    Quantity: <input type="number" name="quantity" value="<%= item.getValue().getQuantity() %>" min="1">
                    <input type="hidden" name="itemId" value="<%= item.getValue().getItemId() %>">
                    <input type="hidden" name="action" value="update">
                    <button class="update" type="submit">Update</button>
                </form>
                <form action="AddToCartServlet?menuId=<%= item.getValue().getItemId() %>" method="post" style="display: inline;">
                    <input type="hidden" name="itemId" value="<%= item.getValue().getItemId() %>">
                    <input type="hidden" name="action" value="remove">
                    <button class="delete" type="submit">Delete</button>
                </form>
            </div>
        </div>
    <% } %>
    </div>
<% 
    } 
%>

</body>
</html>
