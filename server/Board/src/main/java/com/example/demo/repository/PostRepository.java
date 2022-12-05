package com.example.demo.repository;

import com.example.demo.model.ImageFile;
import com.example.demo.model.Product;
import com.example.demo.model.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String insertProductData(Product product){
        String success = "false";
        String sql = "INSERT INTO ProductTB(sellerId, pdPrice, pdTimer, pdSale, topicName, pdCount) VALUES(?,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql, product.getstrId(), product.getPdPrice(),
                product.getPdTimer(), product.getPdSale(), product.getTopicName(), product.getPdCount());
        if(result != 0)
            success = "true";
        Singleton.getInstance().AutoIncresePdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        return success;
    }

    public String insertProductImage(ImageFile imageFile){
        String success = "false";
        int pdId = Singleton.getInstance().AutoIncresePdId;
        String sql = "INSERT INTO PdimageTB(pdId, sellerId, imgUrl) VALUES(?,?,?)";
        int result = jdbcTemplate.update(sql, pdId, imageFile.getsellerId(), imageFile.getimgUrle());
        if(result != 0)
            success = "true";
        return success;
    }
}
