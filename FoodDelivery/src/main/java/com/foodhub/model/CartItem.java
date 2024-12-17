package com.foodhub.model;

public class CartItem {

	private int itemId;
	private int restaurantId;
	private String name;
	private int quantity;
	private boolean isAvailable;
	private float price;
	private float subTotal;
	
	public CartItem(int itemId, int restaurantId, String name, int quantity, float price, boolean isAvailable) {
		super();
		this.itemId = itemId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.isAvailable=isAvailable;
	}
	
	public CartItem(int itemId, int restaurantId, String name, int quantity, float price, float subTotal) {
		super();
		this.itemId = itemId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.subTotal = subTotal;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	@Override
	public String toString() {
		return  itemId + "  " + restaurantId + "  " + name + "  "
				+ quantity + "  " + price +"  "+isAvailable;
	}
	
	
}
