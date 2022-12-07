package com.example.demo.repository;

import com.example.demo.model.*;
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
        String sql = "INSERT INTO ProductTB(sellerId, pdPrice, pdTimer, pdSale, pdName, pdCount, pdContents) VALUES(?,?,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql, product.getstrId(), product.getPdPrice(),
                product.getPdTimer(), product.getPdSale(), product.getpdName(), product.getPdCount(), product.getpdContents());
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

    public List<Post> getOnsalePost(String status){
        String sql = "SELECT ProductTB.pdId, ProductTB.sellerId, pdContents, pdPrice, pdTimer, pdSale, pdName, PdimageTB.imgUrl FROM ProductTB, PdimageTB WHERE (expire < ? AND status = ?) \n" +
                "AND (ProductTB.pdId = PdimageTB.pdId);";
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = date.format(new Date());
        System.out.println(timeStamp);
        List<Post> result = jdbcTemplate.query(sql, new RowMapper<Post>() {
            @Override
            public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
                Post post = new Post();
                post.setPdName(rs.getString("pdName"));

                Integer price = rs.getInt("pdPrice");
                post.setPrice(price);
                Integer discount = rs.getInt("pdSale");
                price = price * (discount / 100);
                post.setSaleprice(price);
                post.setImg(rs.getString("imgUrl"));
                System.out.println(post.getImg());
                post.setPdContents(rs.getString("pdContents"));

                String strSql = "SELECT storeName,storeAddr FROM StoreTB WHERE sellerId = ?";
                List<Store> strResult = jdbcTemplate.query(strSql, new RowMapper<Store>() {
                    @Override
                    public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Store store = new Store();
                        store.setName(rs.getString("storeName"));
                        store.setStoreAddr(rs.getString("storeAddr"));
                        return store;
                    }
                }, rs.getString("sellerId"));
                Store store = strResult.get(0);
                post.setStoreName(store.getName());
                post.setAddress(store.getStoreAddr());

                post.setPdid(rs.getInt("pdId"));
                post.setSellerid(rs.getString("sellerId"));
                post.setPdTimer(rs.getInt("pdTimer"));
                return post;
            }
        }, timeStamp, status);
        System.out.println(result);
        return result;
    }
}
