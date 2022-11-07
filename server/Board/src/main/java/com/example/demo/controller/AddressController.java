package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {

    @GetMapping("/address")
    public String address() {
        System.out.println("카카오 API 테스트");

        return "address";
    }

//    public JSONObject loadLocation(){
//        String REST_KEY = {f900c8bfdae2f9ebc4f577a855cfb872};
//        Double lat = {1};   //위도 좌표
//        Double lon = {1};   //경도 좌표
//        String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=" + lon + "&y=" + lat;
//
//        BufferedReader br = null;
//        JSONObject obj = new JSONObject();
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            URL url = new URL(url);
//            URLConnection conn = url.openConnection();
//            conn.setRequestProperty("Authorization", "KakaoAK " + REST_KEY);
//            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//            if(br != null) obj = mapper.readValue(br, JSONObject.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return obj;
//    }
}