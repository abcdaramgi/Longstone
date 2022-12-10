package com.example.applicationtest.DTO;

public class OnSalePostDTO {
    private String pdName;
    private String address;
    private Integer price;
    private Integer saleprice;
    private String img;
    private String storeName;
    private String pdContents;
    private Integer count;

    private Integer pdid;
    private String sellerid;
    private String status;
    private Integer pdTimer;
    private Float pdSale;
    private String expire;
    private String number;
    private Integer reviewCount;

    public Float getPdSale() {
        return pdSale;
    }

    public void setPdContents(String pdContents) {
        this.pdContents = pdContents;
    }

    public Integer getPdid() {
        return pdid;
    }

    public Integer getPrice() {
        return price;
    }

    public String getPdContents() {
        return pdContents;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setPdid(Integer pdid) {
        this.pdid = pdid;
    }

    public void setPdSale(Float pdSale) {
        this.pdSale = pdSale;
    }

    public void setPdTimer(Integer pdTimer) {
        this.pdTimer = pdTimer;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPdTimer() {
        return pdTimer;
    }

    public String getStatus() {
        return status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getAddress() {
        return address;
    }

    public String getPdName() {
        return pdName;
    }

    public void setSaleprice(Integer saleprice) {
        this.saleprice = saleprice;
    }

    public Integer getSaleprice() {
        return saleprice;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public Integer getCount() { return count; }

    public void setCount(Integer count) { this.count = count; }

    public String getExpire(){return expire;}

    public void setExpire(String expire){this.expire = expire;}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
}
