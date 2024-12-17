package com.foodhub.model;

public class User {
	private int user_id;
	private String username;
	private String email;
	private String password;
	private String address;
	private String role;
	private String mobile;
	
	public User() {
		
	}
	public User(int user_id, String username, String email, String password, String address, String role,String mobile) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
		this.mobile = mobile;
	}
	public User(String username, String email, String password, String address,String mobile) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.mobile = mobile;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return  user_id + "  " + username + "  " + email + "  " + password
				+ "  " + address +"  "+mobile+ "  " + role;
	}
}
