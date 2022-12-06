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
        List<Store> store;
        String sellerId;

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("sellerId : "+messageBody);
        store = storeRepository.findStoreData(messageBody);
        JSONObject data = new JSONObject();
        data.put("store", store);
//        String data = new Gson().toJson(store);
        System.out.println("JsonObject" + data.toString());
        return data;
    }
}
