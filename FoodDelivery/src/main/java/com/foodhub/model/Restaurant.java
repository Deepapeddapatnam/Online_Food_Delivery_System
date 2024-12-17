package com.foodhub.model;

public class Restaurant {
	private int id;
	private String restName;
	private String cuisineType;
	private int deliveryTime;
	private boolean isActive;
	private float rating;
	private String imagePath;

	public Restaurant() {

	}

	public Restaurant(String restName, String cuisineType, int deliveryTime, boolean isActive, float rating,String imagePath) {
		super();
		this.restName = restName;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.isActive = isActive;
		this.rating = rating;
		this.imagePath=imagePath;
	}
	
	
	public Restaurant(int id, String restName, String cuisineType, int deliveryTime, boolean isActive, float rating,String imagePath) {
		super();
		this.id = id;
		this.restName = restName;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.isActive = isActive;
		this.rating = rating;
		this.imagePath=imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return id + "  " + restName + "  " + cuisineType + "  "
				+ deliveryTime + "  " + isActive + "  " + rating+" "+imagePath ;
	}
}
