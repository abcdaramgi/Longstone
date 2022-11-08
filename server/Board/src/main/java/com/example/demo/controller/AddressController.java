package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping(path="/address")
public class AddressController {

    @GetMapping
    public String address() {
        return "어서온나 여기가 주소찾기다";
    }

    @GetMapping("/address.do")
    public JSONObject loadLocation(){
        String REST_KEY = "{f900c8bfdae2f9ebc4f577a855cfb872}";
        Double lat = 37.56667;   //위도 좌표
        Double lon = 126.97806;   //경도 좌표
        String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=" + lon + "&y=" + lat;

        BufferedReader br = null;
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL urll = new URL(url);
            URLConnection conn = urll.openConnection();
            conn.setRequestProperty("Authorization", "KakaoAK " + REST_KEY);
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            if(br != null) obj = mapper.readValue(br, JSONObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return obj;
    }
}