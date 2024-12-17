package com.foodhub.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodhub.dao.MenuDAO;
import com.foodhub.model.Menu;

public class MenuDaoImpl implements MenuDAO {
	private static Connection con;
	private PreparedStatement pstmt;
	private Menu menu;
	int x = -1;
	private ResultSet resultSet;
	private ArrayList<Menu> menuList = new ArrayList<Menu>();
	private static final String INSERT_MENU = "insert into `menu`(restaurantId, itemName, description, price, isAvailable,imagepath) values(?, ? ,? ,? ,? ,?)";
	private static final String GET_ALL_REST_MENU = "select * from `menu` where restaurantId = ?";
	private static final String GET_MENU_BY_ID = "select * from `menu` where menuId = ?";
	private static final String UPDATE_MENU_AVAIL_BY_ID = "update `menu` set isAvailable = ? where menuId = ?";
	private static final String DELETE_MENU_BY_ID = "delete from `menu` where menuId = ?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodhub","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertMenu(Menu m) {
		
		try {
			pstmt = con.prepareStatement(INSERT_MENU);
			pstmt.setInt(1, m.getRid());
			pstmt.setString(2, m.getItemName());
			pstmt.setString(3, m.getDesc());
			pstmt.setFloat(4, m.getPrice());
			pstmt.setBoolean(5, m.isAvailable());
			pstmt.setString(6, m.getImgPath());
			x = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	@Override
	public List<Menu> getAllRestMenu(int restId) {
		
		try {
			pstmt = con.prepareStatement(GET_ALL_REST_MENU);
			pstmt.setInt(1, restId);
			resultSet = pstmt.executeQuery();
			
			menuList  = (ArrayList<Menu>) extractMenuListFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

	@Override
	public Menu getMenuById(int id) {
		
		try {
			pstmt = con.prepareStatement(GET_MENU_BY_ID);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			
			menuList  = (ArrayList<Menu>) extractMenuListFromResultSet(resultSet);
			menu = menuList.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public int updateMenuAvailById(int id, boolean avail) {

		try {
			pstmt = con.prepareStatement(UPDATE_MENU_AVAIL_BY_ID);
			pstmt.setInt(2, id);
			pstmt.setBoolean(1, avail);
			x = pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	@Override
	public int deleteMenuById(int id) {

		try {
			pstmt = con.prepareStatement(DELETE_MENU_BY_ID);
			pstmt.setInt(1, id);
			x = pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
private ArrayList<Menu> extractMenuListFromResultSet(ResultSet resultSet2) {
		
		try {
			while(resultSet.next()) {
				menuList.add(new Menu(resultSet.getInt(1),resultSet.getInt(2),
										resultSet.getString(3),resultSet.getString(4),
										resultSet.getFloat(5),resultSet.getBoolean(6),resultSet.getString(7)));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

}
