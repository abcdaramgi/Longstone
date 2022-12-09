package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class testMessage {
    private String to;
    private String priority;
    private Message data;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {
        private String title;
        private String body;
    }
}
