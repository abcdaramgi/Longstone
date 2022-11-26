package com.example.applicationtest.DTO;

public class postDTO {
    private String originalPrice;
    private String discountPrice;
    private String foodCount;
    private String foodName;

    public String getOriginalPrice(){
        return originalPrice;
    }
    public void setOriginalPrice(String originalPrice){
        this.originalPrice = originalPrice;
    }

    public String getDiscountPrice() {return discountPrice;}
    public void setDiscountPrice(String discountPrice) {this.discountPrice = discountPrice;}

    public String getFoodCount() {return foodCount;}

    public void setFoodCount(String foodCount) {
        this.foodCount = foodCount;
    }

    public String getFoodName(){
        return foodName;
    }
    public void setFoodName(String foodName){
        this.foodName = foodName;
    }
}
