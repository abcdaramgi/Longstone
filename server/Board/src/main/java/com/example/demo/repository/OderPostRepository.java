package com.example.demo.repository;

import com.example.demo.model.OrderList;
import com.example.demo.model.OrderPost;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OderPostRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<OrderList> getOrderListData(String userId){
        String sql = "SELECT ProductTB.pdName, StoreTB.storeName, OrderTB.orderCount, PdimageTB.imgUrl, OrderTB.updateAt \n" +
                "FROM ProductTB, OrderTB, PdimageTB, StoreTB\n" +
                "WHERE OrderTB.userId = ? AND (ProductTB.pdId = OrderTB.pdId AND OrderTB.sellerId = StoreTB.sellerId AND OrderTB.pdId = PdimageTB.pdId)";
        List<OrderList> result = jdbcTemplate.query(sql, new RowMapper<OrderList>() {
            @Override
            public OrderList mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderList orderList = new OrderList();
                orderList.setpdName(rs.getString("pdName"));
                orderList.setstoreName(rs.getString("storeName"));
                orderList.setorderCount(rs.getString("orderCount"));
                orderList.setimgUrl(rs.getString("imgUrl"));
                orderList.setUpdateAt(rs.getString("updateAt"));
                return orderList;
            }
        }, userId);
        return result;
    }

    public void getPostMatchOrderPost(OrderPost orderPost){
        //String sellerId = getSellorId(orderPost);
        String insertSQL = "INSERT INTO OrderTB(userId, pdId, sellerId, orderCount) VALUES (?, ?, (SELECT sellerId FROM ProductTB WHERE pdId = ?), ?)";
        jdbcTemplate.update(insertSQL, orderPost.getUid(), orderPost.getPid(), orderPost.getPid(), orderPost.getPcount());

        String updateCount = "UPDATE ProductTB SET pdCount = pdCount - ?  WHERE pdId = ?;";
        jdbcTemplate.update(updateCount, orderPost.getPcount(), orderPost.getPid());
        // 여기서 seller한테 알림 팅궈줘야해요
        System.out.println("제발요~");
    }

//    public String getSellorId(OrderPost orderPost){
//        String selectSellorIdSQL = "SELECT sellorId FROM ProductTB WHERE pdId = ?";
//        String targetData = orderPost.getPid().toString();
//        String sellerId = jdbcTemplate.queryForObject(selectSellorIdSQL, String.class, targetData);
//
//        return sellerId;
//    }

}
