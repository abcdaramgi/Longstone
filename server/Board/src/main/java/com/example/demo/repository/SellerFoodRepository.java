package com.example.demo.repository;

import com.example.demo.model.storeFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SellerFoodRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<storeFood> selectStoreFoodData(String sellerId){

            String sql = "SELECT ProductTB.sellerId, ProductTB.pdName, ProductTB. pdPrice, ProductTB.pdSale, ProductTB.status, PdimageTB.imgUrl \n" +
                    "FROM ProductTB, PdimageTB\n" +
                    "WHERE ProductTB.sellerId = ? AND ProductTB.pdId = PdimageTB.pdId";

        List<storeFood> result = jdbcTemplate.query(sql, new RowMapper<storeFood>() {
            @Override
            public storeFood mapRow(ResultSet rs, int rowNum) throws SQLException {
                storeFood sf = new storeFood();
                sf.setPdName(rs.getString("pdName"));
                Float price = rs.getFloat("pdPrice");
                sf.setPdPrice(price);
                Float discount = rs.getFloat("pdSale");
                price = price * ((100 - discount) / 100);
                sf.setPdSale(Math.round(price));
                sf.setStatus(rs.getString("status"));
                sf.setImgUrl(rs.getString("imgUrl"));

                String sql2 = "SELECT storeAddr\n" +
                        "FROM StoreTB\n" +
                        "WHERE sellerId = ?";
                List<storeFood> storeResult = jdbcTemplate.query(sql2, new RowMapper<storeFood>() {
                    @Override
                    public storeFood mapRow(ResultSet rs, int rowNum) throws SQLException {
                        storeFood sf2 = new storeFood();
                        sf.setStoreAddr(rs.getString("storeAddr"));
                        return sf2;
                    }
                },rs.getString("sellerId"));


                return sf;
            }
        }, sellerId);

        System.out.println(result);
        return result;

    }


}
