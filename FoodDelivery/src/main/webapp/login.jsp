<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodHub Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
        }
        h2 {
            text-align: center;
            color: #e64f25; /* Dark text */
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-size: 16px;
            color: #333;
        }
        .form-group input {
            width: 94%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        .form-group input:hover {
            border-color: #82c3c5;
            box-shadow: 0 0 5px rgba(130, 195, 197, 0.5);
        }
        .form-group input:focus {
            border-color: #82c3c5;
            outline: none;
        }
        .btn {
            width: 100%;
            padding: 12px;
            background-color: #e64f25;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }
        .btn:hover {
            background-color: #bd2630;
        }
        .link {
            text-align: center;
            margin-top: 15px;
        }
        .link a {
            text-decoration: none;
            color: #e64f25;
        }
        .link a:hover {
            text-decoration: underline;
        }

        /* Popup Notification */
        .popup {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
        }
        .popup-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            width: 300px;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .popup-content p {
            color: #333;
            font-size: 16px;
            margin-bottom: 20px;
        }
        .popup-content button {
            padding: 10px 20px;
            background-color: #e64f25;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .popup-content button:hover {
            background-color: #bd2630;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>FoodHub Login</h2>

        <form action="LoginServlet" method="POST">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>
        <div class="link">
            <p>Don't have an account? <a href="register.html">Register</a></p>
        </div>
    </div>

    <!-- Popup Notification -->
    <div id="popup" class="popup">
        <div class="popup-content">
            <p id="popupMessage"></p>
            <button onclick="closePopup()">Close</button>
        </div>
    </div>

    <script>
        // Function to close the popup
        function closePopup() {
            document.getElementById('popup').style.display = 'none';
        }

        // Display the popup if there's a message
        window.onload = function() {
            <% 
                String loginMessage = (String) session.getAttribute("loginMessage");
                if (loginMessage != null) {
            %>
            document.getElementById('popupMessage').innerText = "<%= loginMessage %>";
            document.getElementById('popup').style.display = 'flex';
            <% 
                session.removeAttribute("loginMessage");
                } 
            %>
        };
    </script>

</body>
</html>
