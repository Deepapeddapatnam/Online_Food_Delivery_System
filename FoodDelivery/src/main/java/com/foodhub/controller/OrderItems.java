package com.foodhub.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodhub.daoimpl.OrderItemDaoImpl;
import com.foodhub.model.OrderItem;


@WebServlet("/OrderItems")
public class OrderItems extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int orderId = Integer.parseInt(req.getParameter("orderId"));
		List<OrderItem> items = new OrderItemDaoImpl().fetchOrderItemByOrderId(orderId);
		req.getSession().setAttribute("items", items);
		resp.sendRedirect("ordereditems.jsp");
		
	}
}
