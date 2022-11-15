package com.example.demo.web.dto;

import com.example.demo.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String price;
    private String foodName;

    @Builder
    public PostsUpdateRequestDto(String price, String foodName) {
        this.price = price;
        this.foodName = foodName;
    }

    public Posts toEntity() {
        return Posts.builder().price(price).foodName(foodName).build();
    }
}
