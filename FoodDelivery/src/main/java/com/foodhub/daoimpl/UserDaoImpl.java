package com.foodhub.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodhub.dao.UserDAO;
import com.foodhub.model.User;

public class UserDaoImpl implements UserDAO {
	private static Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private ArrayList<User> usersList = new ArrayList<User>();
	int x = -1;
	private Statement stmt;
	private User user;
	private static final String INSERT_USER = "insert into `user`(username,email,password,address,mobile) values(?,?,?,?,?)";
	private static final String GET_ALL_USERS = "select * from `user`";
	private static final String GET_USER_BY_ID = "select * from `user` where user_id=?";
	private static final String GET_USER_BY_EMAIL = "select * from `user` where email=?";	
	private static final String DELETE_USER_BY_ID = "delete from `user` where user_id=?";
	private static final String UPDATE_ROLE_BY_ID = "update `user` set role=? where user_id=?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodhub","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public int insertUser(User user) {
		try {
			pstmt = con.prepareStatement(INSERT_USER);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getMobile());
			
			x = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	@Override
	public List<User> getAllUsers() {

		try {
			stmt = con.createStatement();
			resultSet=stmt.executeQuery(GET_ALL_USERS);
			
			usersList = (ArrayList<User>) extractUserListFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	@Override
	public User getUserById(int id) {

		try {
			pstmt = con.prepareStatement(GET_USER_BY_ID);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			usersList = (ArrayList<User>) extractUserListFromResultSet(resultSet);
			user = usersList.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int updateUserById(int id, String role) {
		try {
			pstmt = con.prepareStatement(UPDATE_ROLE_BY_ID);
			pstmt.setInt(2, id);
			pstmt.setString(1, role);
			x = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	@Override
	public int deleteUserById(int id) {

		try {
			pstmt = con.prepareStatement(DELETE_USER_BY_ID);
			pstmt.setInt(1, id);
			x = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public List<User> extractUserListFromResultSet(ResultSet resultSet){
		try {
			while(resultSet.next()) {
				usersList.add(new User(resultSet.getInt(1),resultSet.getString(2),
										resultSet.getString(3),resultSet.getString(4),
										resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	@Override
	public User getUserByEmail(String email) {
		try {
			pstmt = con.prepareStatement(GET_USER_BY_EMAIL);
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			usersList = (ArrayList<User>) extractUserListFromResultSet(resultSet);
			if(usersList.isEmpty()) {
				return null;
			}
			user = usersList.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

}
