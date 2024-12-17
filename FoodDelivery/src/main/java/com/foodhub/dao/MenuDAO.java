package com.foodhub.dao;

import java.util.List;

import com.foodhub.model.Menu;

public interface MenuDAO {
	int insertMenu(Menu m);
	List<Menu> getAllRestMenu(int restId);
	Menu getMenuById(int id);
	int updateMenuAvailById(int id, boolean avail);
	int deleteMenuById(int id);
}
