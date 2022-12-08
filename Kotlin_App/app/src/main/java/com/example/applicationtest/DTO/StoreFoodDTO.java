package com.example.applicationtest.DTO;

public class StoreFoodDTO {
    private String sellerId;
    private String pdName;
    private String storeAddr;
    private Integer pdPrice;
    private Integer pdSale;
    private String status;
    private String imgUrl;

    public String getSellerId() {return sellerId;}
    public void setSellerId(String sellerId) {this.sellerId = sellerId;}

    public String getPdName() {return pdName;}
    public void setPdName(String pdName) {this.pdName = pdName;}

    public String getStoreAddr() {return storeAddr;}
    public void setStoreAddr(String storeAddr) {this.storeAddr = storeAddr;}

    public Integer getPdPrice() {return pdPrice;}
    public void setPdPrice(Integer pdPrice) {this.pdPrice =pdPrice;}

    public Integer getPdSale() {return pdSale;}
    public void setPdSale(Integer pdSale) {this.pdSale = pdSale;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public String getImgUrl() {return imgUrl;}
    public void setImgUrl(String imgUrl) {this.imgUrl = imgUrl;}
}
