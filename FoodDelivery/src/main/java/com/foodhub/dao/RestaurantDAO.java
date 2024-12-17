package com.foodhub.dao;

import java.util.List;

import com.foodhub.model.Restaurant;

public interface RestaurantDAO {
	int insertRestaurant(Restaurant rest);
	List<Restaurant> getAllRestaurants();
	Restaurant getRestaurantById(int id);
	int updateRestaurantById(int id,boolean isAvail);
	int deleteRestaurantById(int id);
}
