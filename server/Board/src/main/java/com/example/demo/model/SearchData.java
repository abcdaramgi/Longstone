package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchData {
    public String content;

    public String getContent(){ return content;}
    public void setContent(String content) {this.content = content;}
    public SearchData(String content){
        this.content = content;
    }
}
