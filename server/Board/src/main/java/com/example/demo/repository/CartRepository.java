package com.example.demo.repository;

import com.example.demo.model.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //장바구니 추가
    public String insertCartData(Cart cart){
        String success = "False";
        String sql = "INSERT INTO CartTB(userId, pdId, sellerId, pdCount) VALUES " +
                "(?, (SELECT pdId FROM ProductTB WHERE pdName = ?), (SELECT sellerId FROM StoreTB WHERE storeName = ?), ?);";
        int result = jdbcTemplate.update(sql, cart.getuserId(), cart.getpdName(), cart.getstoreName(), cart.getpdCount());
        if(result != 0)
            success = "true";
        return success;
    }

    //장바구니 리스트 가져오기
    public List<Cart> getCartListData(String userId){
        String sql = "SELECT StoreTB.storeName, ProductTB.pdName, PdimageTB.imgUrl, ProductTB.pdPrice, CartTB.pdCount\n" +
                "FROM StoreTB, ProductTB, CartTB, PdimageTB\n" +
                "WHERE CartTB.sellerId = ProductTB.sellerId AND CartTB.sellerId = StoreTB.sellerId AND CartTB.pdId = ProductTB.pdId AND CartTB.pdId = PdimageTB.pdId AND CartTB.userId = ?";
        List<Cart> result = jdbcTemplate.query(sql, new RowMapper<Cart>() {
            @Override
            public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
                Cart cartList = new Cart();
                cartList.setstoreName(rs.getString("storeName"));
                cartList.setpdName(rs.getString("pdName"));
                cartList.setimgUrl(rs.getString("imgUrl"));
                cartList.setpdPrice(rs.getInt("pdPrice"));
                cartList.setpdCount(rs.getInt("pdCount"));
                return cartList;
            }
        }, userId);
        return result;
    }
}
