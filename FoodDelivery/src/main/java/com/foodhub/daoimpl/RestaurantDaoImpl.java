package com.foodhub.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodhub.dao.RestaurantDAO;
import com.foodhub.model.Restaurant;

public class RestaurantDaoImpl implements RestaurantDAO {
	private static Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private ArrayList<Restaurant> restaurentList = new ArrayList<Restaurant>();
	private Restaurant rest;
	int x = -1;
	
	private static final String INSERT_USER ="insert into `Restaurant`(name,cuisine_type,delivery_time,isactive,rating,img_Path) VALUES(?,?,?,?,?,?)";
	private static final String GET_ALL_RESTAURENTS = "select * from `restaurant`";
	private static final String GET_REST_BY_ID = "select * from `restaurant` where restaurant_id = ?";
	private static final String UPDATE_ACTIVE_BY_ID = "update `restaurant` set isactive=? where restaurant_id = ?";
	private static final String DELETE_RESTAURENT_BY_ID = "delete from `restaurant` where restaurant_id = ?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodhub","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public int insertRestaurant(Restaurant rest) {

		try {
			pstmt = con.prepareStatement(INSERT_USER);
			pstmt.setString(1, rest.getRestName());
			pstmt.setString(2, rest.getCuisineType());
			pstmt.setInt(3, rest.getDeliveryTime());
			pstmt.setBoolean(4, rest.getIsActive());
			pstmt.setFloat(5, rest.getRating());
			pstmt.setString(6, rest.getImagePath());
			x = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		try {
			stmt = con.createStatement();
			resultSet=stmt.executeQuery(GET_ALL_RESTAURENTS);
			
			restaurentList = (ArrayList<Restaurant>) extractRestListFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurentList;
	}


	@Override
	public Restaurant getRestaurantById(int id) {

		try {
			pstmt = con.prepareStatement(GET_REST_BY_ID);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			restaurentList = (ArrayList<Restaurant>) extractRestListFromResultSet(resultSet);
			rest = restaurentList.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rest;
	}

	@Override
	public int updateRestaurantById(int id, boolean isAvail) {

		try {
			pstmt = con.prepareStatement(UPDATE_ACTIVE_BY_ID);
			pstmt.setInt(2, id);
			pstmt.setBoolean(1, isAvail);
			x = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	@Override
	public int deleteRestaurantById(int id) {
		
		try {
			pstmt = con.prepareStatement(DELETE_RESTAURENT_BY_ID);
			pstmt.setInt(1, id);
			x = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	private ArrayList<Restaurant> extractRestListFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				restaurentList.add(new Restaurant(resultSet.getInt(1),resultSet.getString(2),
										resultSet.getString(3),resultSet.getInt(4),
										resultSet.getBoolean(5),resultSet.getFloat(6),resultSet.getString(7)));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurentList;
	}

}
