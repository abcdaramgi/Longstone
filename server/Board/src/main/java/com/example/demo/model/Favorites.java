package com.example.demo.model;

public class Favorites {
    private String userId;
    private String storeName;

    public String getUserId(){ return userId;}
    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getStoreName(){
        return storeName;
    }
    public void setStoreName(String storeName){
        this.storeName = storeName;
    }
}
