package com.foodhub.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodhub.daoimpl.MenuDaoImpl;
import com.foodhub.model.Cart;
import com.foodhub.model.CartItem;
import com.foodhub.model.Menu;
import com.foodhub.model.User;


@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart==null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		 // Check if the user is logged in
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("loginMessage", "Please log in to access the menu.");
            resp.sendRedirect("login.jsp");
            return; // Stop further processing
        }
        
		String action = req.getParameter("action");
		if("add".equals(action)) 
		{
			addToCart(req,cart);
			resp.sendRedirect("showMenu.jsp");
			return;
		}
		else if("update".equals(action)) 
		{
			updateCart(req,cart);
		}
		else if("remove".equals(action)) 
		{
			removeItem(req,cart);
		}
		else if("clear".equals(action)) {
			clearCart(req,cart);
		}
		session.setAttribute("cart", cart);
		resp.sendRedirect("displayCart.jsp");
	}

	
	
	public void addToCart(HttpServletRequest req, Cart cart)  {

		int itemId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		Menu menuItem = new MenuDaoImpl().getMenuById(itemId);
		
		HttpSession session = req.getSession();		
		session.setAttribute("restaurantId", menuItem.getRid());
		if(menuItem!=null && menuItem.isAvailable()) {
			CartItem item = new CartItem(menuItem.getMid(), menuItem.getRid(), menuItem.getItemName(), quantity, menuItem.getPrice(),menuItem.getPrice()*quantity);
			cart.addItem(item);
			session.setAttribute("addcart", "Item Added"); // Set confirmation attribute
		}
		else {
	        session.setAttribute("addcartError", "Item is not available"); // Set error attribute
	    }
	}

	public void updateCart(HttpServletRequest req, Cart cart) {

		int itemId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		cart.updateItem(itemId, quantity);
	}

	public void removeItem(HttpServletRequest req, Cart cart) {

		int itemId = Integer.parseInt(req.getParameter("id"));
		cart.removeItem(itemId);
	}

	public void clearCart(HttpServletRequest req, Cart cart) {

		cart.clear();
	}

	
}
