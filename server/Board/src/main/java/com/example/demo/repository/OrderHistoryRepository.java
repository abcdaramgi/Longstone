package com.example.demo.repository;

import com.example.demo.model.OrderHistory;
import com.example.demo.model.OrderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderHistoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<OrderHistory> getOrderHistory(String sellerId){
        String sql =
                "SELECT ProductTB.pdName, ProductTB.pdPrice, ProductTB.pdSale, OrderTB.userId, OrderTB.orderCount, OrderTB.updateAt\n" +
                "FROM ProductTB RIGHT OUTER JOIN OrderTB\n" +
                "ON ProductTB.pdId = OrderTB.pdId\n" +
                "WHERE OrderTB.sellerId = ?";

        List<OrderHistory> result = jdbcTemplate.query(sql, new RowMapper<OrderHistory>() {
            @Override
            public OrderHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setPdName(rs.getString("pdName"));
                Float price = rs.getFloat("pdPrice");
                Float discount = rs.getFloat("pdSale");
                price = price * ((100 - discount) / 100);
                orderHistory.setSalePrice(Math.round(price));
                orderHistory.setUserId(rs.getString("userId"));
                orderHistory.setOrderCount(rs.getString("orderCount"));
                orderHistory.setUpdateAt(rs.getString("updateAt"));

                return orderHistory;
            }
        }, sellerId);

        System.out.println(result);
        return result;
    }
}
