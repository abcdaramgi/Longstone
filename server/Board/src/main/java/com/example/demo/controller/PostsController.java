package com.example.demo.controller;

import com.example.demo.domain.posts.Posts;
import com.example.demo.domain.posts.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostsController {
    @Autowired
    PostsRepository postsRepo;

    @GetMapping("/post")
    public String get() {

        //테이블 posts에 insert/update 쿼리를 실행한다.
        //postsRepo.save(Posts.builder().price("15000").foodName("치킨").build());

        List<Posts> list = postsRepo.findAll();
        System.out.println(list.get(0).getFoodName());

        return "hello world";
    }
}
