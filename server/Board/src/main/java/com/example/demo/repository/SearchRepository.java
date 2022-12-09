package com.example.demo.repository;

import com.example.demo.model.SearchData;
import com.example.demo.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SearchRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Store> searchStoreIncludeContent(SearchData searchData){

        //String content = searchData.getContent();
        String sql = "SELECT storeName,storeNum,StoreTB.pdName, StoreTB.imgUrl, StoreTB.storeAddr FROM StoreTB, ProductTB WHERE (topicName LIKE ? OR storeName LIKE ?) AND ProductTB.sellerId = StoreTB.sellerId";
        String targetData = "%" + searchData.content + "%";

        List<Store> result = jdbcTemplate.query(sql, new RowMapper<Store>() {
            @Override
            public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
                Store store = new Store();
                store.setName(rs.getString("storeName"));
                store.setNumber(rs.getString("storeNum"));
                store.setPdname(rs.getString("pdName"));
                store.setImgUrl(rs.getString("imgUrl"));
                store.setStoreAddr(rs.getString("storeAddr"));
                return store;
            }
        }, targetData, targetData);

        return result;
    }
}
