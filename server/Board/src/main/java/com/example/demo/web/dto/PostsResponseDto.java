package com.example.demo.web.dto;

import com.example.demo.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String originalPrice;
    private String discountPrice;
    private String foodCount;
    private String foodName;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.originalPrice = entity.getOriginalPrice();
        this.discountPrice = entity.getDiscountPrice();
        this.foodCount = entity.getFoodCount();
        this.foodName = entity.getFoodName();
    }
}
