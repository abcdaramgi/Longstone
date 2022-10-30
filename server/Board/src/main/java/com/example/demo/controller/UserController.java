package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String check(){
        return "어서온나 이게 스프링 부트다";
    }
    @GetMapping(path="/getusernames")
    public List<String> getAllUserNames(){
        return userRepository.getAllUserNames();
    }
}
