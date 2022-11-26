package com.example.demo.controller;

import com.example.demo.domain.posts.Posts;
import com.example.demo.domain.posts.PostsRepository;
import com.example.demo.model.OrderPost;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(path="/post")
public class PostsController {
    @Autowired
    PostsRepository postsRepo;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public String get() {

        //테이블 posts에 insert/update 쿼리를 실행한다.
        //postsRepo.save(Posts.builder().price("15000").foodName("치킨").build());

        List<Posts> list = postsRepo.findAll();
        System.out.println(list.get(0).getFoodName());

        return "hello world";
    }

    @RequestMapping(value = "/order", method = {RequestMethod.POST})
    public String orderProductPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        OrderPost oderPost = objectMapper.readValue(messagebody, OrderPost.class);





        return "hi";
    }
}
