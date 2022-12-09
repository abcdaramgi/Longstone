package com.example.applicationtest.DTO;

public class CartListDTO {
    public String userId;
    public String storeName;
    public String pdName;
    public String imgUrl;
    public Integer pdPrice;
    public Integer pdCount;
    //===========================================//

    //===========================================//
    public String getuserId(){ return userId;}
    public void setuserId(String userId){
        this.userId = userId;
    }

    public String getstoreName(){ return storeName;}
    public void setstoreName(String storeName){
        this.storeName = storeName;
    }

    public String getpdName(){ return pdName;}
    public void setpdName(String pdName){
        this.pdName = pdName;
    }

    public String getimgUrl(){ return imgUrl;}
    public void setimgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public Integer getpdPrice(){ return pdPrice;}
    public void setpdPrice(Integer pdPrice){
        this.pdPrice = pdPrice;
    }

    public Integer getpdCount(){ return pdCount;}
    public void setpdCount(Integer pdCount){
        this.pdCount = pdCount;
    }
}
