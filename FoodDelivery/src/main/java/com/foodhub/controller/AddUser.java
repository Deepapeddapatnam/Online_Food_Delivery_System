package com.foodhub.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodhub.dao.UserDAO;
import com.foodhub.daoimpl.UserDaoImpl;
import com.foodhub.model.User;

@WebServlet("/addUser")
public class AddUser extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String mobile = req.getParameter("mobile");
		
		User u = new User(name,email,password,address,mobile);
		UserDAO udaoi = new UserDaoImpl();
		
		int status = udaoi.insertUser(u);
		if(status==0) {
			resp.sendRedirect("faliure.html");
		}
		else {
			resp.sendRedirect("success.html");
		}
	}		

}
