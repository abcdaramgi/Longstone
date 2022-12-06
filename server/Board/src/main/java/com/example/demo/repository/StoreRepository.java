package com.example.demo.repository;

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

    public String findStoreImgData(String sellerId){
        String result = "false";

        String sql = "SELECT imgUrl FROM StoreTB WHERE sellerId = ?";
        result = jdbcTemplate.queryForObject(sql, String.class ,sellerId);

        return result;
    }


}
