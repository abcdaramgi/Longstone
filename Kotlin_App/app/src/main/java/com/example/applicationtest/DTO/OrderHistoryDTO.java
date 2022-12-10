package com.example.applicationtest.DTO;

public class OrderHistoryDTO {
    private String userId;
    private String pdName;
    private String orderCount;
    private String updateAt;

    private Integer salePrice;

    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}

    public String getPdName(){
        return pdName;
    }
    public void setPdName(String pdName){
        this.pdName = pdName;
    }

    public String getOrderCount() {return orderCount;}
    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }


    public String getUpdateAt(){
        return updateAt;
    }
    public void setUpdateAt(String updateAt){
        this.updateAt = updateAt;
    }

    public Integer getSalePrice() {return salePrice;}
    public void setSalePrice(Integer slalePrice) {this.salePrice = slalePrice;}
}
