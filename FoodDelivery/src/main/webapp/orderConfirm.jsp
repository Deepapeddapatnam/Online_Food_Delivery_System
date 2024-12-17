<%@page import="com.foodhub.daoimpl.OrdersDaoImppl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.foodhub.model.*, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Confirmation</title>
<style>
    /* General Reset */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: Arial, sans-serif;
    }

    /* Container */
    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
        background-color: #f7f9fc;
        color: #333;
        padding: 20px;
    }

    /* Checkmark Icon */
    .icon-checkmark {
        font-size: 80px;
        color: #28a745;
        margin-bottom: 15px;
    }

    /* Title and Message */
    h1 {
        font-size: 2em;
        color: #333;
        margin-bottom: 10px;
    }

    p {
        font-size: 1em;
        color: #555;
        margin-bottom: 10px;
        text-align: center;
    }

    /* Order Details */
    .order-details {
        margin-top: 20px;
        padding: 15px;
        border: 1px solid #ddd;
        border-radius: 8px;
        background-color: #ffffff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 80%;
        max-width: 400px;
    }

    .order-details p {
        font-size: 1em;
        color: #444;
        margin: 10px 0;
    }

    .order-details p strong {
        color: #333;
    }

    /* Continue Shopping Link */
    a {
        display: inline-block;
        margin-top: 20px;
        padding: 10px 20px;
        background-color: #F3631B;
        color: #fff;
        text-decoration: none;
        border-radius: 5px;
        transition: background-color 0.3s ease;
    }

    a:hover {
        background-color: #0056b3;
    }

    /* Media Query for Responsiveness */
    @media (max-width: 600px) {
        .container {
            padding: 10px;
        }

        .order-details {
            width: 90%;
        }

        h1 {
            font-size: 1.8em;
        }

        p, .order-details p {
            font-size: 0.95em;
        }
    }
</style>
</head>
<body>
    <div class="container">
        <div class="icon-checkmark">&#10003;</div>
        <h1>Your Order is Placed!</h1>
        <p>Thank you for shopping with us. Your order has been successfully placed.</p>
        
        <% User user = (User)session.getAttribute("user"); 
        List<Orders> orders = (List<Orders>)	new OrdersDaoImppl().fetchOrdersByUserId(user.getUser_id());
        		for(Orders order:orders){
        %>
        
        <div class="order-details">
            <p><strong>Order ID:</strong> <%=order.getOrderId() %></p>
            <p><strong>Total Amount:</strong> &#8377;<%=order.getTotalAmount() %></p>
            <p><strong>Status:</strong> <%=order.getStatus() %></p>
            <p><strong>Payment Method:</strong> <%=order.getPaymentOption() %></p>
        </div>
		<%} %>
        <a href="home.jsp" onclick="returnToHome()">Continue Shopping</a>
    </div>

    <script>
        function returnToHome() {
            alert('Redirecting to the home page...');
        }
    </script>
</body>
</html>
