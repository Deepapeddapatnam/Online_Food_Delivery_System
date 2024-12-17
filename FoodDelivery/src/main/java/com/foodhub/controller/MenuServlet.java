package com.foodhub.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodhub.daoimpl.MenuDaoImpl;
import com.foodhub.daoimpl.RestaurantDaoImpl;
import com.foodhub.model.Menu;
import com.foodhub.model.Restaurant;


@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private List<Menu> menuList;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rid = Integer.parseInt(req.getParameter("rid"));
		MenuDaoImpl mdaoi = new MenuDaoImpl();
		RestaurantDaoImpl rdao = new RestaurantDaoImpl();
		Restaurant rest = rdao.getRestaurantById(rid);
		req.getSession().setAttribute("restName", rest.getRestName());
		menuList = mdaoi.getAllRestMenu(rid);
		req.getSession().setAttribute("menuList", menuList);
		resp.sendRedirect("showMenu.jsp");
		
		
	
	}

}
