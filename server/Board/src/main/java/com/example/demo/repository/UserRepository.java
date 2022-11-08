package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository //해당 클래스를 루트 컨테이너에 빈(Bean) 객체로 생성해주는 어노테이션
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<String> getAllUserNames(){
        List<String> usernameList = new ArrayList<>();
        usernameList.addAll(jdbcTemplate.queryForList("SELECT name FROM test;", String.class));
        return usernameList;
    }
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