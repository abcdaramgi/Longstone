package hello.hellospring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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



}
