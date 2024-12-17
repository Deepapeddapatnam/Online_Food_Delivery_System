package com.foodhub.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodhub.daoimpl.OrderHistoryDaoImpl;
import com.foodhub.model.User;


@WebServlet("/OrderHistory")
public class OrderHistory extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			int uid = user.getUser_id();
			OrderHistoryDaoImpl ohdaoi = new OrderHistoryDaoImpl();
			List<com.foodhub.model.OrderHistory> orderHistory = ohdaoi.fetchOrderHistoryByUserId(uid);
			session.setAttribute("orderHistory", orderHistory);
		}
		resp.sendRedirect("orderHistory.jsp");
	}

}
