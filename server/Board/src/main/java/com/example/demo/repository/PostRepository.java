package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String insertProductData(Product product){
        String success = "false";
        String sql = "INSERT INTO ProductTB(pdId, sellerId, pdPrice, pdTimer, pdSale, topicName, pdCount) VALUES(?,?,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql, 4, product.getstrId(), product.getPdPrice(),
                product.getPdTimer(), product.getPdSale(), product.getTopicName(), product.getPdCount());
        if(result != 0)
            success = "true";
        return success;
    }

    public String insertProductImage(String savepath){
        String success = "false";
        String sql = "INSERT INTO PdimageTB(pdId, sellerId, imgUrl) VALUES(?,?,?)";
        int result = jdbcTemplate.update(sql, 4, "seller1123", savepath);
        if(result != 0)
            success = "true";
        return success;
    }
}
