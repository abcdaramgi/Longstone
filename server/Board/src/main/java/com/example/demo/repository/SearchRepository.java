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
        String sql = "SELECT storeName,storeNum,pdName FROM StoreTB, ProductTB WHERE (topicName LIKE ? OR storeName LIKE ?) AND ProductTB.sellerId = StoreTB.sellerId";
        String targetData = "%" + searchData.content + "%";
//        List<Store> result = jdbcTemplate.query("SELECT storeName \n" +
//                "FROM StoreTB, ProductTB \n" +
//                "WHERE (topicName LIKE '%" + "?" + "%' OR storeName LIKE '%" + "?" + "%') AND ProductTB.sellerId = StoreTB.sellerId", new RowMapper<Store>() {
//            @Override
//            public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Store store = new Store(rs.getString("storeName")
//                        ,rs.getString("storeNum")
//                        ,rs.getString("pdName"));
//                return store;
//            }
//        }, searchData.content, searchData.content);
        //});
        RowMapper<Store> rowMapper = (rs, rowNum) -> new Store(rs.getString("storeName")
                ,rs.getString("storeNum")
                ,rs.getString("pdName"));
        List<Store> testlist = jdbcTemplate.query(sql, rowMapper, targetData, targetData);
        return testlist;
    }
}
