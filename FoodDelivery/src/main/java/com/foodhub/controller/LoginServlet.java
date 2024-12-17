package com.foodhub.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodhub.daoimpl.UserDaoImpl;
import com.foodhub.model.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserDaoImpl udaoi = new UserDaoImpl();
		User u = udaoi.getUserByEmail(email);
		if(u!=null) {
			String password2 = u.getPassword();
			if(password.equals(password2)) {
				HttpSession session = req.getSession();
				session.setAttribute("user", u);
				
				resp.sendRedirect("HomeServlet");
			}
			else {
				resp.sendRedirect("pwdMismatch");
			}
		}
		else {
			resp.sendRedirect("register.html");
		}
	}

}
