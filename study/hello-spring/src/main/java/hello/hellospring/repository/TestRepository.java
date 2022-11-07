package hello.hellospring.repository;

import hello.hellospring.model.User;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//해당 클래스를 루트 컨테이너에 빈(Bean) 객체로 생성해주는 어노테이션
@Repository
public class TestRepository {
    //의존성 주입
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<User> selectAll(){
        List<User> result = jdbcTemplate.query("SELECT name, email FROM test", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("name")
                                    ,rs.getString("email"));
                return user;
            }
        });


//        List<User> usernameList = new ArrayList<>();
//        User user = new User();
//        user.name = jdbcTemplate.queryForList("SELECT name FROM test;", String.class).toString();
//        user.email = jdbcTemplate.queryForList("SELECT email FROM test;", String.class).toString();
//        usernameList.add(user);
//        return usernameList;

//        List<User> userDataList = new ArrayList<User>();
//        userDataList.addAll(jdbcTemplate.queryForList("SELECT name, email FROM test;", String.class));
//        return userDataList;
        return result;
    }
}
