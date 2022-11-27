package com.example.demo.model;

public class Product {

    public String strId;
    public String pdPrice;
    public String pdTimer;
    public String pdSale;
    public String topicName;
    public String pdCount;

    public String getstrId(){ return strId;}
    public void setStrId(String strId){
        this.strId = strId;
    }

    public String getPdPrice(){ return pdPrice;}
    public void setPdPrice(String pdPrice){
        this.pdPrice = pdPrice;
    }

    public String getPdTimer(){ return pdTimer;}
    public void setPdTimer(String pdTimer){ this.pdTimer = pdTimer; }

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


}
