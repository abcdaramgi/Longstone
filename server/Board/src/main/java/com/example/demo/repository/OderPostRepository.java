package com.example.demo.repository;

import com.example.demo.model.OrderPost;
import com.example.demo.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OderPostRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void getPostMatchOrderPost(OrderPost orderPost){
        //String sellerId = getSellorId(orderPost);
        String insertSQL = "INSERT INTO OrderTB(id, userId, pdId, sellerId, orderCount) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertSQL, 1, orderPost.getUid(), orderPost.getPid(), "seller1123", orderPost.getPcount());

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
