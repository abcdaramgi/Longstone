package com.example.demo.controller;
import com.example.demo.model.Favorites;
import com.example.demo.model.RequestDTO;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.repository.SellerRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.catalina.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserController {

    //Jackson라이브러리 클래스
    //JSON을 Java 객체로 deserialization
    //Java 객체를 JSON으로 serialization 할 때 사용
    private ObjectMapper objectMapper = new ObjectMapper();


    @Autowired  //의존성 주입
    UserRepository userRepository;
    @Autowired
    SellerRepository sellerRepository;

    @GetMapping
    public String check(){
        return "CD/CD 데모 영상 코드 변환 ";
    }

    //tset
    @GetMapping(path="/getusernames")
    public List<String> getAllUserNames(){
        return userRepository.getAllUserNames();
    }

    //test
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

    //회원가입
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public String registerUserData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        User userData = objectMapper.readValue(messageBody, User.class);
        System.out.println("id : " + userData.id + "\n" +
                "pw : " + userData.pw + "\n" +
                "nickname : " + userData.nickname + "\n" +
                "name : " + userData.name + "\n" +
                "email : " + userData.email + "\n" +
                "phone : " + userData.phone + "\n" +
                "birth : " + userData.birth);
        success = userRepository.insertRegisterData(userData);
        return success;
    }

    //로그인
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String loginUserData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println(messageBody);
        User userData = objectMapper.readValue(messageBody, User.class);
        System.out.println("id : " + userData.id + "\n" +
                "pw : " + userData.pw);
        success = userRepository.selectLoginData(userData);
        return success;
    }

    //즐겨찾기
    @RequestMapping(value = "/favorites", method = {RequestMethod.POST})
    public String favoritesUserData(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        Favorites favorites = objectMapper.readValue(messageBody, Favorites.class);
        System.out.println("userId : " + favorites.getUserId() + "\n" +
                "storeName : " + favorites.getStoreName());
        success = userRepository.insertFavoritesData(favorites);
        return success;
    }

    //즐겨찾기 해제
    @RequestMapping(value = "/clearfavorites", method = {RequestMethod.POST})
    public String clearFavoritesUserData(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        Favorites favorites = objectMapper.readValue(messageBody, Favorites.class);
        System.out.println("userId : " + favorites.getUserId() + "\n" +
                "storeName : " + favorites.getStoreName());
        success = userRepository.updateFavoritesData(favorites);
        return success;
    }

    //즐겨찾기한 가게정보 가져오기
    @RequestMapping(value = "/infofavorites", method = {RequestMethod.POST})
    public JSONObject getFavoritesStoreData(HttpServletRequest request, HttpServletResponse response) throws IOException{

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("userId : " + messageBody);
        List<Store> store = userRepository.getFavoritesData(messageBody);
        JSONObject data = new JSONObject();
        data.put("store", store);
        return data;
    }

    //판매자로그인
    @RequestMapping(value = "/sellerlogin", method = {RequestMethod.POST})
    public String loginSellerData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        User userData = objectMapper.readValue(messageBody, User.class);
        System.out.println("id : " + userData.id + "\n" +
                "pw : " + userData.pw);
        success = sellerRepository.selectSellerLoginData(userData);
        return success;
    }

    @PostMapping(value = "/savetoken")
    public String saveToken(@RequestBody RequestDTO requestDTO) throws IOException {
        String success = "false";

        if(requestDTO.getType() == "user"){
            success = userRepository.saveToken(requestDTO.getId(), requestDTO.getTargetToken());
        }else{
            success = sellerRepository.saveToken(requestDTO.getId(), requestDTO.getTargetToken());
        }

        return success;
    }

}
