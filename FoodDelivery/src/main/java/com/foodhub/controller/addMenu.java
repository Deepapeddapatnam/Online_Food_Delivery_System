package com.foodhub.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodhub.dao.MenuDAO;
import com.foodhub.daoimpl.MenuDaoImpl;
import com.foodhub.model.Menu;

@WebServlet("/addMenu")
public class addMenu extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int restaurant_id = Integer.parseInt(req.getParameter("restaurant_id"));
		String item_name = req.getParameter("item_name");
		String description = req.getParameter("description");
		float price = Float.parseFloat(req.getParameter("price"));
		String availability = req.getParameter("availability");
		boolean isAvail;
		if(availability.equals("Yes")) {
			isAvail=true;
		}
		else {
			isAvail=false;
		}
		String image_path = req.getParameter("image_path");
		
		Menu m = new Menu(restaurant_id, item_name, description, price, isAvail, image_path);
		MenuDAO mdaoi = new MenuDaoImpl();
		int status = mdaoi.insertMenu(m);
		if(status==0) {
			resp.sendRedirect("failure.html");
		}
		else {
			resp.sendRedirect("success.html");
		}
	}

}
