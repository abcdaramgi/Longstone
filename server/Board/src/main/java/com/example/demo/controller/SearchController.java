package com.example.demo.controller;

import com.example.demo.model.SearchData;
import com.example.demo.model.Store;
import com.example.demo.repository.SearchRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(path="/search")
public class SearchController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    SearchRepository searchRepository;

    @GetMapping
    public String search() {
        return "어서온나 여기가 검색이다";
    }

//    @GetMapping(path="/search.do")
//    public JSONObject selectInfo(Model model){
//
//    }

    @RequestMapping(value = "/topic", method = {RequestMethod.POST})
    public JSONObject searchTopicData(HttpServletRequest request, HttpServletResponse response) throws IOException{

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        SearchData searchData = objectMapper.readValue(messageBody, SearchData.class);
        List<Store> store = searchRepository.searchStoreIncludeContent(searchData);
        JSONObject data = new JSONObject();
        data.put("store", store);

        return data;
    }
}
