package hello.hellospring.controller;

import hello.hellospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/user") //url localhost:8080/user 이면 낚아채기
public class UserController {

    //의존성 주입
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String check(){
        return "어서온나 이게 스프링부트다";
    }

    @GetMapping(path="/getusernames")
    public List<String> getAllUserNames(){
        return userRepository.getAllUserNames();
    }
}
