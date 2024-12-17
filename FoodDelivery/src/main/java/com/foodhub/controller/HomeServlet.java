package com.foodhub.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodhub.daoimpl.RestaurantDaoImpl;
import com.foodhub.model.Restaurant;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDaoImpl rdaoi = new RestaurantDaoImpl();
		List<Restaurant> allRest = rdaoi.getAllRestaurants();
		HttpSession session = req.getSession();
		session.setAttribute("allRest", allRest);
		resp.sendRedirect("home.jsp");
	}

}
