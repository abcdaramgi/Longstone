package com.example.demo.model;

public class OrderList {
    private String pdName;
    private String storeName;
    private String orderCount;
    private String imgUrl;
    private String updateAt;

    public String getpdName(){
        return pdName;
    }
    public void setpdName(String pdName){
        this.pdName = pdName;
    }

    public String getstoreName() {return storeName;}
    public void setstoreName(String storeName) {this.storeName = storeName;}

    public String getorderCount() {return orderCount;}
    public void setorderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getimgUrl(){
        return imgUrl;
    }
    public void setimgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public String getUpdateAt(){
        return updateAt;
    }
    public void setUpdateAt(String updateAt){
        this.updateAt = updateAt;
    }
}
