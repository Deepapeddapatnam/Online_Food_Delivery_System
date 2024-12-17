<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.foodhub.model.User, java.util.List, com.foodhub.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
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
        }
        
        /* Navbar styling */
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #F3631B; /* Tomato color for FoodHub */
            padding: 0 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        
        .navbar .logo {
            font-size: 24px;
            color: white;
            font-weight: bold;
        }
        
        .navbar .nav-items {
            display: flex;
            align-items: center;
        }

        .navbar .nav-items a,
        .navbar .nav-items h2,
        .navbar .nav-items button {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            margin: 0 5px;
            font-size: 16px;
            transition: background 0.3s ease;
            border-radius: 5px;
            background-color: transparent;
            border: none;
            cursor: pointer;
        }

        .navbar .nav-items a:hover,
        .navbar .nav-items button:hover {
            background-color: rgba(255, 255, 255, 0.2);
        }
        
        /* Cart and Order History Icons */
        .icon-button {
            background-color: #ECD5C2; /* Light background color */
            color: #F3631B; /* Tomato color text */
            padding: 10px;
            border-radius: 50%;
            font-size: 20px;
            cursor: pointer;
            transition: transform 0.3s;
        }
        
        .icon-button:hover {
            transform: scale(1.1);
        }

        /* Dropdown styling */
        .dropdown {
            position: relative;
            display: inline-block;
        }
        .dropdown .dropbtn {
            background-color: #ECD5C2;
            color: #F3631B;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 20px;
            padding: 10px 15px;
            cursor: pointer;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #F1AB7C;
            min-width: 160px;
            right: 0;
            box-shadow: 0px 8px 16px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        /* Card container styling */
        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            margin: 40px auto;
            width: 90%;
        }
        
        .card {
            background-color: #fff;
            width: 30%;
            margin: 10px;
            padding: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
            transition: transform 0.3s ease;
        }
        
        .card:hover {
            transform: translateY(-5px);
        }

        .card h3 {
            margin-top: 0;
        }

        .card p {
            margin: 5px 0;
        }
        
        /* Responsive design */
        @media (max-width: 768px) {
            .card {
                width: 100%;
            }
            .navbar .nav-items a,
            .navbar .nav-items button {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

<% User u = (User)session.getAttribute("user"); %>

<!-- Navigation Bar -->
<div class="navbar">
    <!-- Logo -->
    <div class="logo">FoodHub</div>
    
    <!-- Navbar items -->
    <div class="nav-items">
        <% if (u != null) { %>
            <h2 style="text-align: center;">Hello, <%= u.getUsername() %>! Welcome </h2>
            <!-- Cart and Order History -->
            <a class="icon-button" onclick="location.href='displayCart.jsp'">🛒</a>
            <button class="icon-button" onclick="location.href='OrderHistory'">📜</button>
            
            <!-- Profile Dropdown -->
            <div class="dropdown">
                <button class="dropbtn">Profile</button>
                <div class="dropdown-content">
                    <a href="profile.jsp">Name: <%= u.getUsername() %></a>
                    <a href="changePassword.jsp">Change Password</a>
                    <a href="logout.jsp">Logout</a>
                </div>
            </div>
        <% } else { %>
            <!-- Login/Register for non-logged in users -->
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
        <% } %>
    </div>
</div>

<!-- Restaurant Cards (3 per row) -->
<% List<Restaurant> allRest = (List<Restaurant>) session.getAttribute("allRest"); %>
<div class="card-container">
    <% if (allRest != null) {
        for (int i = 0; i < allRest.size(); i++) {
            Restaurant r = allRest.get(i); 
    %>
        <div class="card">
            <img src="<%= request.getContextPath() %>/images/<%= r.getImagePath() %>" alt="Restaurant Item Image" width="100%" height="250px" style="border-radius: 10px;">
            <h3><%= r.getRestName() %></h3>
            <p>Cuisine: <%= r.getCuisineType() %></p>
            <p>Rating: <%= r.getRating() %></p>
            <p>Delivery Time: <%= r.getDeliveryTime() %> mins</p>
            <a href="MenuServlet?rid=<%=r.getId()%>"><button>View Menu</button></a>
        </div>
        <% if ((i + 1) % 3 == 0) { %>
            </div><div class="card-container">
        <% }
        }
    } %>
</div>

</body>
</html>
