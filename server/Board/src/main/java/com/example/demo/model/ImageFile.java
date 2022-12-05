package com.example.demo.model;

public class ImageFile {
    private String sellerId;
    private String imgUrl;

    public String getsellerId(){ return sellerId;}
    public void setsellerId(String sellerId){
        this.sellerId = sellerId;
    }

    public String getimgUrle(){
        return imgUrl;
    }
    public void setimgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
}
