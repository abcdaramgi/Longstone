package com.example.demo.repository;

import com.example.demo.model.ImageFile;
import com.example.demo.model.Post;
import com.example.demo.model.Product;
import com.example.demo.model.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

        //방금 넣은거 expiredate timestamp식으로 찍어줄라고
        String expireSql = "UPDATE ProductTB SET expire = createAt + ? WHERE pdId = ?";
        jdbcTemplate.update(expireSql, product.getPdTimer(),Singleton.getInstance().AutoIncresePdId);

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

    public List<Post> getOnsalePost(){
        String sql = "SELECT pdId, sellerId, pdContents, pdPrice, pdTimer, pdSale FROM ProductTB WHERE expire <= ?;";
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        String timeStamp = date.format(new Date());
        List<Post> result = jdbcTemplate.query(sql, new RowMapper<Post>() {
            @Override
            public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
                Post post = new Post();
                post.setPdName(rs.getString("pdName"));
                post.setPdid(rs.getInt("pdId"));
                post.setSellerid(rs.getString("sellerId"));
                post.setPdContents(rs.getString("pdContents"));
                post.setPrice(rs.getInt("pdPrice"));
                post.setPdTimer(rs.getInt("pdTimer"));
                return post;
            }
        }, timeStamp);
        return result;
    }
}
