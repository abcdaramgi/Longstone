package com.example.demo.controller;

import com.example.demo.domain.posts.Posts;
import com.example.demo.domain.posts.PostsRepository;
import com.example.demo.model.ImageFile;
import com.example.demo.model.OrderPost;
import com.example.demo.model.Product;
import com.example.demo.repository.OderPostRepository;
import com.example.demo.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(path="/post")
public class PostsController {
    @Autowired
    PostsRepository postsRepo;
    @Autowired
    PostRepository postRepository;
    @Autowired
    OderPostRepository oderPostRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

//    @GetMapping
//    public String get() {
//
//        //테이블 posts에 insert/update 쿼리를 실행한다.
//        //postsRepo.save(Posts.builder().price("15000").foodName("치킨").build());
//
//        List<Posts> list = postsRepo.findAll();
//        System.out.println(list.get(0).getFoodName());
//
//        return "hello world";
//    }

    @RequestMapping(value = "/order", method = {RequestMethod.POST})
    public String orderProductPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        OrderPost oderPost = objectMapper.readValue(messagebody, OrderPost.class);
        oderPostRepository.getPostMatchOrderPost(oderPost);

        return "hi";
    }

    @RequestMapping(value = "/product", method = {RequestMethod.POST})
    public String uploadProduct(HttpServletRequest request, HttpServletResponse response ) throws IOException{
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        Product product = objectMapper.readValue(messageBody, Product.class);
        System.out.println(
                "strId : " + product.strId + "\n" +
                "pdPrice : " + product.pdPrice + "\n" +
                "pdTimer : " + product.pdTimer + "\n" +
                "pdSale : " + product.pdSale + "\n" +
                "topicName : " + product.topicName + "\n" +
                "pdCount : " + product.pdCount);
        success = postRepository.insertProductData(product);
        return success;
    }

    @RequestMapping(value = "/image", method = {RequestMethod.POST})
    public String uploadProductImage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String success = "false";
        String sellerId = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iterator = multipartRequest.getFileNames();

        while(iterator.hasNext()){
            String key = (String) iterator.next();
            for(MultipartFile file : multipartRequest.getFiles(key)){
                System.out.println("file name : " + file.getName());
                System.out.println("file size : " + file.getSize());

                String savePath = "C:\\Users\\kddns\\Documents\\test\\" + file.getOriginalFilename();
                System.out.println("seller name : " + sellerId);
                System.out.println("save file path : " + savePath);

                ImageFile imagefile = new ImageFile();
                imagefile.setsellerId(file.getName());
                imagefile.setimgUrl(savePath);

                file.transferTo(new File(savePath));
                success = postRepository.insertProductImage(imagefile);
            }
        }

        return success;
    }
}
