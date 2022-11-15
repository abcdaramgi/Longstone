package com.example.demo.web.dto;

import com.example.demo.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String price;
    private String foodName;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.foodName = entity.getFoodName();
    }
}
