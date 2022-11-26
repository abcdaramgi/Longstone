package com.example.demo.repository;

import com.example.demo.model.OrderPost;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OderPostRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void getPostMatchOrderPost(OrderPost orderPost, User user){
        String userid = user.getId();
        String ssql = "SELECT userId, sellorId";

        String isql = "INSERT INTO OrderTB(id, userId, pdId, sellerId, orderCount) VALUES (?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(isql, "잉", "wwa1102", orderPost.getPid(), "sellor1123", orderPost.getPcount());


        //evalReply.setMember(accountUtil.getLoginMember());










        String sql = "SELECT storeName,storeNum,pdName FROM StoreTB, ProductTB WHERE (topicName LIKE ? OR storeName LIKE ?) AND ProductTB.sellerId = StoreTB.sellerId";
        //String targetData = "%" + orderPost.content + "%";
    }
}
