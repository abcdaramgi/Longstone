package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {
    private String targetToken;
    private String topic;
    private String title;
    private String body;
    private String type;
    private String id;
}