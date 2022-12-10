package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Store {
    public String name;
    public String number;
    public String pdname;
    public String openHour;
    public String storeAddr;

    public String imgUrl;

    public String sellerId;
    public Integer pdPrice;
    public Integer pdSale;

    public String getName() { return name; }

    public String getNumber() {
        return number;
    }

    public String getPdname() {
        return pdname;
    }

    public String getOpenHour() {
        return openHour;
    }

    public Integer getpdPrice() { return pdPrice; }
    public Integer getpdSale() { return pdSale; }
    public String getStoreAddr() {
        return storeAddr;
    }

    public String getImgUrl() {
        return imgUrl;
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

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public Store(String name, String number, String pdname){
        this.name = name;
        this.number = number;
        this.pdname = pdname;
    }

    public Store(String name, String number, String storeAddr, String openHour){
        this.name = name;
        this.number = number;
        this.openHour = openHour;
        this.storeAddr = storeAddr;
    }

    public Store(String name, String number, String pdname, String imgUrl, String storeAddr){
        this.name = name;
        this.number = number;
        this.pdname = pdname;
        this.imgUrl = imgUrl;
        this.storeAddr = storeAddr;
    }

}
