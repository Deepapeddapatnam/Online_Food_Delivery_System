package com.foodhub.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodhub.dao.OrderHistoryDAO;
import com.foodhub.model.OrderHistory;

public class OrderHistoryDaoImpl implements OrderHistoryDAO {
	private static final String INSERT_ORDER_HISTORY = "INSERT INTO ordersHistory (userId, orderId, total, status) VALUES (?, ?, ?, ?)";
    private static final String FETCH_ORDER_HISTORY_BY_USERID = "SELECT * FROM ordersHistory WHERE userId = ?";
    private static final String UPDATE_ORDER_HISTORY_STATUS_BY_ID = "UPDATE ordersHistory SET status = ? WHERE orderHistory_id = ?";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/foodhub";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private Connection con;

    // Constructor to initialize the connection
    public OrderHistoryDaoImpl() {
        try {
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertOrderHistory(OrderHistory oh) {
        int result = -1;
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_ORDER_HISTORY)) {
            pstmt.setInt(1, oh.getUserId());
            pstmt.setInt(2, oh.getOrderId());
            pstmt.setFloat(3, oh.getTotal());
            pstmt.setString(4, oh.getStatus());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<OrderHistory> fetchOrderHistoryByUserId(int uid) {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ORDER_HISTORY_BY_USERID)) {
            pstmt.setInt(1, uid);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orderHistoryList.add(new OrderHistory(
                        rs.getInt("orderHistory_id"),
                        rs.getInt("userId"),
                        rs.getInt("orderId"),
                        rs.getTimestamp("date"),
                        rs.getFloat("total"),
                        rs.getString("status")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public int updateOrderHistory(int ohid, String status) {
        int result = -1;
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_ORDER_HISTORY_STATUS_BY_ID)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, ohid);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Close the connection when the DAO is no longer in use
    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
