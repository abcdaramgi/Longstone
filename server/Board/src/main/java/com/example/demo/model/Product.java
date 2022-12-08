package com.example.demo.model;

public class Product {

    public String strId;
    public String pdPrice;
    public Integer pdTimer;
    public String pdSale;
    public String topicName;
    public String pdCount;
    public String pdContents;
    public String pdName;

    public String getstrId(){ return strId;}
    public void setStrId(String strId){
        this.strId = strId;
    }

    public String getPdPrice(){ return pdPrice;}
    public void setPdPrice(String pdPrice){
        this.pdPrice = pdPrice;
    }

    public Integer getPdTimer(){ return pdTimer;}
    public void setPdTimer(Integer pdTimer){ this.pdTimer = pdTimer; }

    public String getPdSale(){ return pdSale;}
    public void setPdSale(String pdSale){
        this.pdSale = pdSale;
    }

    public String getTopicName(){ return topicName;}
    public void setTopicName(String topicName){
        this.topicName = topicName;
    }

    public String getPdCount(){ return pdCount;}
    public void setPdCount(String pdCount){
        this.pdCount = pdCount;
    }

    public String getpdContents(){ return pdContents;}
    public void setpdContents(String pdContents){
        this.pdContents = pdContents;
    }

    public String getpdName(){ return pdName;}
    public void setpdName(String pdName){
        this.pdName = pdName;
    }
}
