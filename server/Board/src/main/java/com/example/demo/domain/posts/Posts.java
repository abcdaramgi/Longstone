package com.example.demo.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter

@NoArgsConstructor
@Entity

public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String originalPrice;

    @Column(length = 500, nullable = false)
    private String discountPrice;

    @Column(length = 500, nullable = false)
    private String foodCount;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String foodName;


    @Builder
    public Posts(String originalPrice, String discountPrice, String foodCount, String foodName) {
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.foodCount = foodCount;
        this.foodName = foodName;
    }
    public void update(String originalPrice, String discountPrice, String foodCount, String foodName) {
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.foodCount = foodCount;
        this.foodName = foodName;
    }
}
