package com.foodhub.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodhub.dao.OrdersDAO;
import com.foodhub.model.Orders;

public class OrdersDaoImppl implements OrdersDAO {
	private static Connection con;
    private PreparedStatement pstmt;
	private ResultSet resultSet;
	private static Orders order;
	private ArrayList<Orders> orderList = new ArrayList<Orders>();
	private int x=-1;
    private static final String ORDERS_BY_OID = "SELECT * FROM orders WHERE orderId=?";
    private static final String INSERT_ORDERS = "INSERT INTO orders (user_Id, restaurant_Id, total_amount, status, payment_option) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER_STATUS_BY_ID = "UPDATE orders SET status = ? WHERE orderId = ?";
    private static final String GET_ORDERS_BY_UID = "SELECT * FROM orders WHERE user_Id = ?";
    private static final String maxOrderId = "SELECT max(orderId) FROM orders";
    
    static {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodhub","root","root");
			System.out.println("connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public Orders fetchOrdersByOrderId(int id) {

        try  {
        	pstmt = con.prepareStatement(ORDERS_BY_OID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

            orderList  = (ArrayList<Orders>) extractOrderListFromResultSet(resultSet);
            order = orderList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    

	@Override
    public int insertOrderDetails(Orders order) {

        try {
        	pstmt = con.prepareStatement(INSERT_ORDERS);
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurentId());
            pstmt.setFloat(3, order.getTotalAmount());
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getPaymentOption());

            x = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return x;
    }

    @Override
    public int updateOrderStatusById(int id, String status) {
        

        try{
        	pstmt = con.prepareStatement(UPDATE_ORDER_STATUS_BY_ID);
            pstmt.setString(1, status);
            pstmt.setInt(2, id);

            x = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public List<Orders> fetchOrdersByUserId(int uid) {        

        try{
        	pstmt = con.prepareStatement(GET_ORDERS_BY_UID);
            pstmt.setInt(1, uid);
            resultSet = pstmt.executeQuery();

            orderList = (ArrayList<Orders>) extractOrderListFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }
    
    private List<Orders> extractOrderListFromResultSet(ResultSet resultSet2) {
		// TODO Auto-generated method stub
		try {
			while(resultSet.next()) {
			    orderList.add(new Orders(resultSet.getInt(1),
			                             resultSet.getInt("userId"),
			                             resultSet.getInt("restaurantId"),
			                             resultSet.getFloat("total_amount"),
			                             resultSet.getString("status"),
			                             resultSet.getTimestamp("order_date"),
			                             resultSet.getString("payment_option")));
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}



	@Override
	public int getMaxOrderId() {
		int maxid = 0;
		try {
			Statement statement = con.createStatement();
			resultSet = statement.executeQuery(maxOrderId);
			if(resultSet.next()) {
				maxid =  resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return maxid;
	}
}
