package com.foodhub.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodhub.daoimpl.OrderHistoryDaoImpl;
import com.foodhub.daoimpl.OrderItemDaoImpl;
import com.foodhub.daoimpl.OrdersDaoImppl;
import com.foodhub.model.Cart;
import com.foodhub.model.CartItem;
import com.foodhub.model.OrderHistory;
import com.foodhub.model.OrderItem;
import com.foodhub.model.Orders;
import com.foodhub.model.User;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String paymentMode = req.getParameter("selectedPayment");
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		int restIt = (int) session.getAttribute("restaurantId");
		float totalAmount=0;
		for(CartItem item : cart.getItems().values()) {
			
			totalAmount += item.getPrice() * item.getQuantity();
		}
		if(cart!=null && user!=null && !cart.getItems().isEmpty()) {
			
			// insert into orders table
			Orders order = new Orders(user.getUser_id(), restIt, totalAmount, "pending" , paymentMode);
			OrdersDaoImppl odaoi = new OrdersDaoImppl();
			odaoi.insertOrderDetails(order);
			int orderId = odaoi.getMaxOrderId();
			
			// insert into orderItem table
			for(CartItem item : cart.getItems().values()) {
				int menuid = item.getItemId();
				int qunatity = item.getQuantity();
				float total = item.getSubTotal();
				OrderItem orderItem = new OrderItem(orderId, menuid, qunatity, total);
				new OrderItemDaoImpl().insertOrderItem(orderItem);
			}
			
			// insert into orderhistory 
			OrderHistory orderHistory = new OrderHistory(user.getUser_id(), orderId, totalAmount, "");
			new OrderHistoryDaoImpl().insertOrderHistory(orderHistory);
		}
		resp.sendRedirect("orderConfirm.jsp");
	}
}
