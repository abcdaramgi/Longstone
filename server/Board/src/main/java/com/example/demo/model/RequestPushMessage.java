package com.example.demo.model;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
//@Schema(name = "푸쉬 메시지")
public class RequestPushMessage {
    //@Schema(description = "제목")
    String title;
    //@Schema(description = "내용")
    String body;
    //@Schema(description = "데이터")
    Map<String, String> data;
    //@Schema(description = "이미지주소")
    String image;
    //@Schema(description = "대상 사용자 번호")
    List<Long> userNos;

    @Builder
    public RequestPushMessage(String title, String body, Map<String, String> data, String image, List<Long> userNos) {
        this.title = title;
        this.body = body;
        this.data = data;
        this.image = image;
        this.userNos = userNos;
    }
}