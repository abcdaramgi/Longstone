package com.example.demo.repository;
import com.example.demo.model.Store;
import com.example.demo.model.Post;
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

            String sql = "SELECT ProductTB.sellerId, ProductTB.pdName, ProductTB. pdPrice, ProductTB.pdSale, ProductTB.status, PdimageTB.imgUrl\n" +
                    "FROM ProductTB LEFT OUTER JOIN PdimageTB\n" +
                    "ON ProductTB.pdId = PdimageTB.pdId\n" +
                    "WHERE ProductTB.sellerId = ?";

        List<storeFood> result = jdbcTemplate.query(sql, new RowMapper<storeFood>() {
            @Override
            public storeFood mapRow(ResultSet rs, int rowNum) throws SQLException {
                storeFood sf = new storeFood();
                sf.setPdName(rs.getString("pdName"));
                Float price = rs.getFloat("pdPrice");
                sf.setPdPrice(price);
                Float discount = rs.getFloat("pdSale");
                price = price * ((100 - discount) / 100);
                sf.setSaleprice(Math.round(price));
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

    public String deleteStoreFoodData(Store store){
        String success = "왔다감";

        String openSql = "SET foreign_key_checks = 0;";
        String deleteProduct = "DELETE FROM ProductTB WHERE sellerId = ? AND pdName = ?;";
        String deletePdimage = "DELETE FROM PdimageTB WHERE pdId = (SELECT pdId FROM ProductTB WHERE sellerId = ? AND pdName = ?);";
        String deleteOrder = "DELETE FROM OrderTB WHERE pdId = (SELECT pdId FROM ProductTB WHERE sellerId = ? AND pdName = ?);";
        String deleteCart = "DELETE FROM CartTB WHERE pdId = (SELECT pdId FROM ProductTB WHERE sellerId = ? AND pdName = ?);";
        String closeSql = "SET foreign_key_checks = 1;";

        jdbcTemplate.update(openSql);
        jdbcTemplate.update(deleteProduct, store.getSellerId(), store.getPdname());
        jdbcTemplate.update(deletePdimage, store.getSellerId(), store.getPdname());
        jdbcTemplate.update(deleteOrder, store.getSellerId(), store.getPdname());
        jdbcTemplate.update(deleteCart, store.getSellerId(), store.getPdname());
        jdbcTemplate.update(closeSql);

        return success;

    }

    public String updateFoodStatus(Post post){
        String success = "False";


        String sql = "UPDATE ProductTB SET STATUS = ? WHERE pdId = (SELECT pdId FROM ProductTB WHERE sellerId = ? AND pdName = ?)";
        int result = jdbcTemplate.update(sql, post.getStatus(), post.getSellerid(), post.getPdName());
        if(result != 0)
            success = "true";
        return success;
    }
}
