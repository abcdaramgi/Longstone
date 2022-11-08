package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/user")
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
    @GetMapping(path="/json.do")
    public JSONObject selectInfo(Model model) {
        List<User> users = userRepository.selectAll();
        JSONObject data = new JSONObject();
        data.put("member", users);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }

}
