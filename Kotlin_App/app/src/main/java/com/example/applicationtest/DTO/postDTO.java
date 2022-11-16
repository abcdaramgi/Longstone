package com.example.applicationtest.DTO;

public class postDTO {
    private String price;
    private String foodName;

    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }

    public String getFoodName(){
        return foodName;
    }
    public void setFoodName(String foodName){
        this.foodName = foodName;
    }
}
