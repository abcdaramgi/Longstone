package com.example.demo.web.dto;

import com.example.demo.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String originalPrice;
    private String discountPrice;
    private String foodCount;
    private String foodName;

    @Builder
    public PostsUpdateRequestDto(String originalPrice, String discountPrice, String foodCount, String foodName) {
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.foodCount = foodCount;
        this.foodName = foodName;
    }

    public Posts toEntity() {
        return Posts.builder().originalPrice(originalPrice).discountPrice(discountPrice).foodCount(foodCount).foodName(foodName).build();
    }
}
