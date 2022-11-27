package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SellerRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String selectSellerLoginData(User user){
        String success = "False";
        String sql = "SELECT count(*) FROM SellTB WHERE sellerId=? AND phoneNum=?";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, user.getId(), user.getPw());
        if(result != 0)
            success = "true";
        return success;
    }
}
