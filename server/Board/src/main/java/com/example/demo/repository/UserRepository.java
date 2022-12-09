package com.example.demo.repository;

import com.example.demo.model.Favorites;
import com.example.demo.model.Store;
import com.example.demo.model.User;
//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;


@Repository //해당 클래스를 루트 컨테이너에 빈(Bean) 객체로 생성해주는 어노테이션
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //회원가입
    public String insertRegisterData(User user){
        String success = "False";
        String sql = "INSERT INTO UserTB(userId, userPassword, nickName, userName, birthDate, phoneNum, userMail)" +
                "VALUES(?,?,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql, user.getId(), user.getPw(),
                user.getNickname(), user.getName(), user.getBirth(), user.getPhone(), user.getEmail());
        if(result != 0)
            success = "true";
        return success;
    }

    //로그인
    public String selectLoginData(User user){
        String success = "False";
        String sql = "SELECT count(*) FROM UserTB WHERE userId=? AND userPassword=?";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, user.getId(), user.getPw());
        if(result != 0)
            success = "true";
        return success;
    }

    //사용자 즐겨찾기 설정
    public String insertFavoritesData(Favorites favorites){
        String success = "False";
        String sql = "INSERT INTO UsertopicTB (userId, sellerId) VALUES (?, (SELECT sellerId FROM StoreTB WHERE storeName = ?))";
        int result = jdbcTemplate.update(sql, favorites.getUserId(), favorites.getStoreName());
        if(result != 0)
            success = "true";
        return success;
    }

    //사용자 즐겨찾기 설정해제
    public String updateFavoritesData(Favorites favorites){
        String success = "False";
        String sql = "UPDATE UsertopicTB SET status = 'Y' WHERE userId = ? AND sellerId = (SELECT sellerId FROM StoreTB WHERE storeName = ?)";
        int result = jdbcTemplate.update(sql, favorites.getUserId(), favorites.getStoreName());
        if(result != 0)
            success = "true";
        return success;
    }

    //사용자가 즐겨찾기한 가게정보 가져오기
    public List<Store> getFavoritesData(String userId){
        String sql = "SELECT storeName, pdName, imgUrl, sellerId FROM StoreTB WHERE StoreTB.sellerId = any(SELECT sellerId FROM UsertopicTB WHERE userId = ?)";
        List<Store> result = jdbcTemplate.query(sql, new RowMapper<Store>() {
            @Override
            public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
                Store store = new Store();
                store.setName(rs.getString("storeName"));
                store.setPdname(rs.getString("pdName"));
                store.setImgUrl(rs.getString("imgUrl"));
                store.setSellerId(rs.getString("sellerId"));
                return store;
            }
        }, userId);
        return result;
    }

    public String saveToken(String id, String token){
        String success = "False";
        String sql = "UPDATE UserTB SET nToken = ? WHERE userId = ?";
        int result = jdbcTemplate.update(sql, token, id);
        if(result != 0)
            success = "true";
        return success;
    }

    //test
    public List<String> getAllUserNames(){
        List<String> usernameList = new ArrayList<>();
        usernameList.addAll(jdbcTemplate.queryForList("SELECT name FROM test;", String.class));
        return usernameList;
    }

    //test
    public List<User> selectAll() {
        List<User> result = jdbcTemplate.query("SELECT name, email FROM test", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("name")
                        , rs.getString("email"));
                return user;
            }
        });
        return result;
    }
}