<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodhub.model.Menu, java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Restaurant List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        }

        /* Header styling */
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #388e3c;
            color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        
        .header b {
            font-size: 28px;
        }

        /* Button styling */
        button {
            width: 130px;
            height: 40px;
            border: none;
            background-color: #F3631B;
            color: white;
            border-radius: 20px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        button:hover {
            background-color: #e65100;
            transform: scale(1.05);
        }

        /* Card container styling */
        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin: 20px auto;
            width: 90%;
        }

        /* Card styling */
        .card {
            background-color: #fff;
            width: 45%;
            margin: 15px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-align: center;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }

        /* Card text styling */
        .card h2 {
            font-size: 20px;
            color: #388e3c;
            margin-bottom: 10px;
        }

        .card p {
            font-size: 16px;
            color: #555;
            margin-bottom: 8px;
        }

        /* Price styling */
        .card .price {
            font-size: 20px;
            font-weight: bold;
            color: #D32F2F;
            margin-top: 10px;
        }

        /* Cart icon styling */
        .cart-icon {
            font-size: 36px;
            color: white;
        }

        /* Error message styling */
        .error-message {
            color: red;
            font-weight: bold;
            font-size: 20px;
            text-align: center;
            margin-top: 20px;
        }
        
        /* Responsive Design */
        @media (max-width: 768px) {
            .card {
                width: 100%;
            }
        }
    </style>
</head>
<body>

<% 
   if (session.getAttribute("addcart") != null) { 
%>
   <script>
       // Display a notification popup
       document.addEventListener("DOMContentLoaded", function() {
           alert("Item added to cart successfully!");
       });
   </script>
   <% 
       session.removeAttribute("addcart"); // Clear the attribute after displaying
    }
	%>

<% 
   if (session.getAttribute("addcartError") != null) { 
%>
   <script>
       document.addEventListener("DOMContentLoaded", function() {
           alert("Item is not available and could not be added to the cart.");
       });
   </script>
   <% 
       session.removeAttribute("addcartError"); // Clear the attribute after displaying
    } 
    %>

<div class="header">
    <a href="HomeServlet"><button>Go Home</button></a>
    <b>Welcome to <% out.println(session.getAttribute("restName")); %></b>
    <a href="displayCart.jsp"><i class="fa fa-shopping-cart cart-icon"></i></a>
</div>

<h1 style="text-align: center; color: #388e3c; margin-top: 20px;">Menu List</h1>

<% if (session.getAttribute("menuIsAlreadyAdded") != null) { %>
    <div class="error-message"><%= session.getAttribute("menuIsAlreadyAdded") %></div>
<% } %>

<div class="card-container">
    <% ArrayList<Menu> result = (ArrayList<Menu>) session.getAttribute("menuList");
    	if(result!=null){
        for (Menu menuItem : result) {
    %>
    <div class="card">
        <img src="<%= request.getContextPath() %>/images/<%= menuItem.getImgPath() %>" alt="Menu Item Image" width="100%" height="250px" style="border-radius: 10px;">
        <h2><strong>Item name:</strong> <%= menuItem.getItemName() %></h2>
        <p><strong>Description:</strong> <%= menuItem.getDesc() %></p>
        <p class="price"><strong>Price: ₹</strong> <%= menuItem.getPrice() %></p>
        <p><strong>Available:</strong> <% out.println(menuItem.isAvailable() ? "Yes" : "No"); %></p>
        
        <!-- <a href="addToCart?menuId=<%= menuItem.getMid() %>">
            <button>Add to Cart</button>
        </a> -->
        
        <form action="AddToCartServlet?menuId=<%= menuItem.getMid()%>" method="POST">
        	Quantity<input type="number" name="quantity" value="1" min="1" class="quantity-input">
        	<input type="submit" value="add to cart" class="add-to-cart-btn">
        	<input type="hidden" name="action" value="add">
        </form>
    </div>
    <% }}else { %>
    <p>No menu items available</p>
    <%} %>
</div>

</body>
</html>
