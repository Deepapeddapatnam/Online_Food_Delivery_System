package com.foodhub.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodhub.dao.OrderItemDAO;
import com.foodhub.model.OrderItem;

public class OrderItemDaoImpl implements OrderItemDAO {
	private static Connection con;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private static final String INSERT_ORDER_ITEM = "INSERT INTO orderItems (orderId, menuId, quantity, subTotal) VALUES (?, ?, ?, ?)";
    private static final String FETCH_ORDER_ITEMS_BY_OID = "SELECT * FROM orderItems WHERE orderId = ?";
    private int x = -1;
    private ArrayList<OrderItem> orderItemsList = new ArrayList<OrderItem>();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodhub", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertOrderItem(OrderItem oi) {
        try {
            pstmt = con.prepareStatement(INSERT_ORDER_ITEM);
            pstmt.setInt(1, oi.getOrderId());
            pstmt.setInt(2, oi.getMenuId());
            pstmt.setInt(3, oi.getQuantity());
            pstmt.setFloat(4, oi.getSubTotal());

            x = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public List<OrderItem> fetchOrderItemByOrderId(int oid) {
        try {
            pstmt = con.prepareStatement(FETCH_ORDER_ITEMS_BY_OID);
            pstmt.setInt(1, oid);
            resultSet = pstmt.executeQuery();

            orderItemsList = (ArrayList<OrderItem>) extractOrderItemsFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemsList;
    }

    private List<OrderItem> extractOrderItemsFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                orderItemsList.add(new OrderItem(
                        resultSet.getInt("orderItem_id"),
                        resultSet.getInt("orderId"),
                        resultSet.getInt("menuId"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("subTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemsList;
    }
}
