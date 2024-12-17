package com.foodhub.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodhub.dao.RestaurantDAO;
import com.foodhub.daoimpl.RestaurantDaoImpl;
import com.foodhub.model.Restaurant;

@WebServlet("/addRestaurant")
public class addRestaurant extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restaurant_name = req.getParameter("restaurant_name");
		String cuisine_type = req.getParameter("cuisine_type");
		int delivery_time = Integer.parseInt(req.getParameter("delivery_time"));
		String status = req.getParameter("status");
		boolean isActive;
		if(status.equals("Open")) {
			isActive=true;
		}
		else {
			isActive=false;
		}
		float rating = Float.parseFloat(req.getParameter("rating"));
		String image_path = req.getParameter("image_path");
		
		Restaurant r = new Restaurant(restaurant_name,cuisine_type,delivery_time,isActive,rating,image_path);
		RestaurantDAO rdaoi = new RestaurantDaoImpl();
		int x = rdaoi.insertRestaurant(r);
		if(x==0) {
			resp.sendRedirect("failure.html");
		}
		else {
			resp.sendRedirect("success.html");
		}
		
	}

}
