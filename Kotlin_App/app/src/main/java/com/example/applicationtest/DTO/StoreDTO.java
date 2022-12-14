package com.example.applicationtest.DTO;

import java.util.stream.Stream;

public class StoreDTO {
    public String name;
    public String pdname;
    public String imgUrl;
    public String number;
    public String openHour;
    public String storeAddr;
    public Integer pdPrice;
    public Integer pdSale;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPdname() {
        return pdname;
    }

    public String getOpenHour() {
        return openHour;
    }

    public String getStoreAddr() {
        return storeAddr;
    }

    public String getImgUrl() {
        return imgUrl;
    }
    public Integer getpdPrice() {
        return pdPrice;
    }
    public Integer getpdSale() {
        return pdSale;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setpdPrice(Integer pdPrice) {
        this.pdPrice = pdPrice;
    }
    public void setpdSale(Integer pdSale) {
        this.pdSale = pdSale;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }
    public void setStoreAddr(String storeAddr) {
        this.storeAddr = storeAddr;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public StoreDTO(){

    }
    public StoreDTO(String name, String number, String pdname){
        this.name = name;
        this.number = number;
        this.pdname = pdname;
    }

    public StoreDTO(String name, String number, String storeAddr, String openHour){
        this.name = name;
        this.number = number;
        this.storeAddr = storeAddr;
        this.openHour = openHour;
    }

}
