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
    private String price;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String foodName;


    @Builder
    public Posts(String price, String foodName) {
        this.price = price;
        this.foodName = foodName;
    }
    public void update(String price, String foodName) {
        this.price = price;
        this.foodName = foodName;
    }
}
