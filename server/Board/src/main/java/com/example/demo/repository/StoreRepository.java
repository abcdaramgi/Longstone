package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StoreRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //가게 정보 가져오기
    public List<Store> findStoreData(String sellerId){
        String sql = "SELECT storeName,storeNum,openHour, storeAddr FROM StoreTB WHERE sellerId = ?";
        List<Store> result = jdbcTemplate.query(sql, new RowMapper<Store>() {
            @Override
            public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
                Store store = new Store(
                        rs.getString("storeName"),
                        rs.getString("storeNum"),
                        rs.getString("storeAddr"),
                        rs.getString("openHour"));
                return store;
            }
        }, sellerId);
        return result;
    }

    //가게 이미지 가져오기
    public String findStoreImgData(String sellerId){
        String result = "false";

        String sql = "SELECT imgUrl FROM StoreTB WHERE sellerId = ?";
        result = jdbcTemplate.queryForObject(sql, String.class ,sellerId);

        return result;
    }

    //가게이름으로 가게 이미지 가져오기
    public String findStoreImgData2(String storeName){
        String result = "false";

        String sql = "SELECT imgUrl FROM StoreTB WHERE storeName = ?";
        result = jdbcTemplate.queryForObject(sql, String.class ,storeName);

        return result;
    }

    // 가게정보 모두 들고오기
    public List<Store> SearchStoreData(String status){
        String sql = "SELECT storeName, pdName, imgUrl FROM StoreTB";
        List<Store> result = jdbcTemplate.query(sql, new RowMapper<Store>() {
            @Override
            public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
                Store store = new Store();
                store.setName(rs.getString("storeName"));
                store.setPdname(rs.getString("pdName"));
                store.setImgUrl(rs.getString("imgUrl"));
                return store;
            }
        });
        return result;
    }

    public List<Store> getStoreDetailListData(String storeName){
        String sql = "SELECT ProductTB.pdName, imgUrl, pdPrice, pdSale " +
                "FROM ProductTB, PdimageTB " +
                "WHERE ProductTB.sellerId = (SELECT sellerId FROM StoreTB WHERE storeName = ?) AND ProductTB.pdId = PdimageTB.pdId;";
        List<Store> result = jdbcTemplate.query(sql, new RowMapper<Store>() {
            @Override
            public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
                Store storeList = new Store();
                storeList.setPdname(rs.getString("pdName"));
                storeList.setImgUrl(rs.getString("imgUrl"));
                storeList.setpdPrice(rs.getInt("pdPrice"));
                storeList.setpdSale(rs.getInt("pdSale"));
                return storeList;
            }
        }, storeName);
        return result;
    }
}
