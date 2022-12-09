package com.example.demo.controller;

import com.example.demo.model.Store;
import com.example.demo.repository.StoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(path="/store")
public class StoreController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    StoreRepository storeRepository;

    //가게정보 가져오기
    @RequestMapping(value = "/info", method = {RequestMethod.POST})
    public JSONObject getStoreInfoData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sellerId;

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("sellerId : "+messageBody);

        List<Store> store = storeRepository.findStoreData(messageBody);
        JSONObject data = new JSONObject();
        data.put("store", store);
        return data;
    }

    //가게이미지 가져오기
    @RequestMapping(value = "/imginfo", method = {RequestMethod.POST})
    public String getStoreImgData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File storeImg = null;

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("sellerId : "+messageBody);

        String imgUrl = storeRepository.findStoreImgData(messageBody);

        System.out.println(imgUrl);
        return imgUrl;
    }

    @RequestMapping(value = "/storedata", method = {RequestMethod.POST})
    public JSONObject getSearchStoreData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("postStatus : " + messageBody);
        List<Store> store = storeRepository.SearchStoreData(messageBody);
        JSONObject data = new JSONObject();
        data.put("store", store);
        return data;
    }
}
