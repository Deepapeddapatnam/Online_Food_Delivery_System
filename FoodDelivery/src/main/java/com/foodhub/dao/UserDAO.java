package com.foodhub.dao;

import java.util.List;

import com.foodhub.model.User;

public interface UserDAO {
	int insertUser(User user);
	List<User> getAllUsers();
	User getUserById(int id);
	User getUserByEmail(String email);
	int updateUserById(int id,String address);
	int deleteUserById(int id);
}
