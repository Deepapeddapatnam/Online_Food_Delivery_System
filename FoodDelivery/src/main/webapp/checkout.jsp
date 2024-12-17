
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodhub.model.*, java.util.Map,java.util.Set" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <style>
        /* General styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }

        /* Navigation bar styling */
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: white;
            padding: 10px 20px;
            border-radius: 8px;
        }

        .navbar .back-button,
        .navbar .cart-button {
            background-color: white;
            color: #F3631B;
            padding: 8px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-weight: bold;
        }

        

		 .navbar .back-button:hover,
		 .navbar .cart-button:hover {
            background-color: #e2e6ea;
        }
        /* Container styling */
        .container {
            max-width: 700px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            margin-top: 20px;
        }

        h2 {
            color: #F3631B;
            text-align: center;
        }

        .table-container {
            margin-top: 20px;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            overflow: hidden;
        }

        /* Table styling */
        .cart-table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
        }

        .cart-table th, .cart-table td {
            padding: 12px;
            text-align: center;
            font-size: 16px;
            border: 1px solid #e0e0e0;
        }

        .cart-table th {
            background-color: #388e3c;
            color: white;
        }

        .cart-table td {
            color: #333;
        }

        .total-amount {
            font-size: 20px;
            color: #D32F2F;
            font-weight: bold;
            text-align: center;
            margin: 20px 0;
        }

        /* Form and button styling */
        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #388e3c;
            font-size: 16px;
        }

        .form-group input, .form-group select, .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .form-group textarea {
            resize: vertical;
        }

        .submit-btn {
            width: 100%;
            padding: 10px;
            background-color: #F3631B;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
        }

        .submit-btn:hover {
            background-color: #e65100;
        }

        /* Payment options */
        .payment-option {
            display: flex;
            justify-content: space-between;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .payment-option.selected {
            background-color: #388e3c;
            color: white;
        }

        .payment-details {
            display: none;
            margin-top: 10px;
        }
    </style>
    
</head>
<body>

<script>
function showPaymentDetails(paymentMode, element) {
    console.log("Selected Payment Mode:", paymentMode); // Log selected mode
    const creditDetails = document.getElementById("credit-details");
    const debitDetails = document.getElementById("debit-details");
    const upiDetails = document.getElementById("upi-details");
    const codDetails = document.getElementById("cod-details");
    const selectedPayment = document.getElementById("selectedPayment");

    // Hide all payment details initially
    creditDetails.style.display = "none";
    debitDetails.style.display = "none";
    upiDetails.style.display = "none";
    codDetails.style.display = "none";

    const options = document.querySelectorAll('.payment-option');
    options.forEach(option => option.classList.remove('selected'));

    // Show relevant payment details based on selected option
    if (paymentMode === "Credit Card") {
        creditDetails.style.display = "block";
    } else if (paymentMode === "Debit Card") {
        debitDetails.style.display = "block";
    } else if (paymentMode === "UPI") {
        upiDetails.style.display = "block";
    } else if (paymentMode === "Cash on Delivery") {
        codDetails.style.display = "block";
    }

    selectedPayment.value = paymentMode;
    element.classList.add('selected');
}
</script>

<div class="navbar">
    <a href="javascript:history.back()" class="back-button">Back</a>
    <h2>Checkout</h2>
    <a href="CartPage.jsp" class="cart-button">Cart</a>
</div>

<div class="container">
    <%
    Cart cart = (Cart) session.getAttribute("cart");
    Map<Integer, CartItem> result = cart != null ? cart.getItems() : null;
    Set<Integer> set = result != null ? result.keySet() : null;
    double totalamount = 0;
%>

<% if (set != null && !set.isEmpty()) { %>
    <div class="table-container">
        <table class="cart-table">
            <tr>
                <th>Item Name</th>
                <th>Price</th>
                <th>SubTotal</th>
                <th>Quantity</th>
            </tr>
            <% for (Integer key : set) {
                CartItem item = result.get(key);
                totalamount += item.getSubTotal();
                session.setAttribute("totalAmount", totalamount);
            %>
            <tr>
                <td><%= item.getName() %></td>
                <td class="price">₹<%= item.getPrice() %></td>
                <td>₹<%= item.getSubTotal() %></td>
                <td><%= item.getQuantity() %></td>
            </tr>
            <% } %>
        </table>
    </div>
<% } else { %>
    <p class="empty-cart">No items in the cart.</p>
<% } %>

<p class="total-amount">Total Amount: ₹<%= String.format("%.2f", totalamount) %></p>

<form action="${pageContext.request.contextPath}/Checkout" method="POST">
    <div class="form-group">
        <label for="houseNo">House No.</label>
        <input type="text" id="houseNo" name="houseNo" required>
    </div>
    <!-- Additional address fields can go here... -->

    <div class="form-group">
        <label>Select Payment Mode</label>
        <div class="payment-option" onclick="showPaymentDetails('Credit Card', this)">
            <span>Credit Card</span>
        </div>
        <div class="payment-option" onclick="showPaymentDetails('Debit Card', this)">
            <span>Debit Card</span>
        </div>
        <div class="payment-option" onclick="showPaymentDetails('UPI', this)">
            <span>UPI</span>
        </div>
        <div class="payment-option" onclick="showPaymentDetails('Cash on Delivery', this)">
            <span>Cash on Delivery</span>
        </div>
    </div>
    <input type="hidden" id="selectedPayment" name="selectedPayment">

    <!-- Payment Details Sections -->
    <div id="credit-details" class="payment-details">
        <h3>Credit Card Details</h3>
        <div class="form-group">
            <label for="creditCardNumber">Card Number</label>
            <input type="text" id="creditCardNumber" name="creditCardNumber" >
        </div>
        <div class="form-group">
            <label for="creditExpiryDate">Expiry Date</label>
            <input type="text" id="creditExpiryDate" name="creditExpiryDate"  placeholder="MM/YY">
        </div>
        <div class="form-group">
            <label for="creditCvv">CVV</label>
            <input type="text" id="creditCvv" name="creditCvv" >
        </div>
    </div>

    <div id="debit-details" class="payment-details">
        <h3>Debit Card Details</h3>
        <div class="form-group">
            <label for="debitCardNumber">Card Number</label>
            <input type="text" id="debitCardNumber" name="debitCardNumber" >
        </div>
        <div class="form-group">
            <label for="debitExpiryDate">Expiry Date</label>
            <input type="text" id="debitExpiryDate" name="debitExpiryDate"  placeholder="MM/YY">
        </div>
        <div class="form-group">
            <label for="debitCvv">CVV</label>
            <input type="text" id="debitCvv" name="debitCvv" >
        </div>
    </div>

    <div id="upi-details" class="payment-details">
        <h3>UPI Details</h3>
        <div class="form-group">
            <label for="upiId">UPI ID</label>
            <input type="text" id="upiId" name="upiId" >
        </div>
    </div>

    <div id="cod-details" class="payment-details">
        <h3>Cash on Delivery</h3>
        <p>No additional details required for Cash on Delivery.</p>
    </div>

    <button type="submit" class="submit-btn">Place Order</button>
</form>

</div>

</body>
</html>
