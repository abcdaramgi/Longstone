package com.example.demo.model;

public class storeFood {
    private String pdId;
    private String sellerId;
    private String pdName;
    private String storeAddr;
    private Float pdPrice;
    private Integer pdSale;
    private String status;
    private String imgUrl;
    private Integer saleprice;

    public String getPdId() {return pdId;}
    public void setPdId(String pdId) {this.pdId = pdId;}

    public Integer getSaleprice() {return saleprice;}
    public void setSaleprice(Integer slalePrice) {this.saleprice = slalePrice;}

    public String getSellerId() {return sellerId;}
    public void setSellerId(String sellerId) {this.sellerId = sellerId;}

    public String getPdName() {return pdName;}
    public void setPdName(String pdName) {this.pdName = pdName;}

    public String getStoreAddr() {return storeAddr;}
    public void setStoreAddr(String storeAddr) {this.storeAddr = storeAddr;}

    public Float getPdPrice() {return pdPrice;}
    public void setPdPrice(Float pdPrice) {this.pdPrice =pdPrice;}

    public Integer getPdSale() {return pdSale;}
    public void setPdSale(Integer pdSale) {this.pdSale = pdSale;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public String getImgUrl() {return imgUrl;}
    public void setImgUrl(String imgUrl) {this.imgUrl = imgUrl;}



}
