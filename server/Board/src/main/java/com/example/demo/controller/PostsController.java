package com.example.demo.controller;

import com.example.demo.domain.posts.PostsRepository;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
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
    @Autowired
    CartRepository cartRepository;

    @Autowired
    SellerFoodRepository sellerFoodRepository;

    @Autowired
    OrderHistoryRepository orderHistoryRepository;

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

    @RequestMapping(value = "/cart", method = {RequestMethod.POST})
    public String cartPost(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        Cart cart = objectMapper.readValue(messageBody, Cart.class);
        System.out.println("userId : " + cart.getuserId() + "\n" +
                "pdName : " + cart.getpdName() + "\n" +
                "storeName : " + cart.getstoreName() + "\n" +
                "pdCount : " + cart.getpdCount());
        success = cartRepository.insertCartData(cart);
        return success;
    }

    @RequestMapping(value = "/cartlist", method = {RequestMethod.POST})
    public JSONObject cartListPost(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("userId : " + messageBody);
        List<Cart> cartList = cartRepository.getCartListData(messageBody);
        JSONObject data = new JSONObject();
        data.put("cartList", cartList);
        return data;
    }

    @RequestMapping(value = "/order", method = {RequestMethod.POST})
    public String orderProductPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        OrderPost oderPost = objectMapper.readValue(messagebody, OrderPost.class);
        oderPostRepository.getPostMatchOrderPost(oderPost);

        return "hi";
    }

    @RequestMapping(value = "/orderlist", method = {RequestMethod.POST})
    public JSONObject orderListPost(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("userId : " + messageBody);
        List<OrderList> orderList = oderPostRepository.getOrderListData(messageBody);
        JSONObject data = new JSONObject();
        data.put("orderList", orderList);
        return data;
    }

    @RequestMapping(value = "/product", method = {RequestMethod.POST})
    public String uploadProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        Product product = objectMapper.readValue(messageBody, Product.class);
        System.out.println(
                "strId : " + product.strId + "\n" +
                        "pdPrice : " + product.pdPrice + "\n" +
                        "pdTimer : " + product.pdTimer + "\n" +
                        "pdSale : " + product.pdSale + "\n" +
                        "pdName : " + product.pdName + "\n" +
                        "pdCount : " + product.pdCount + "\n" +
                        "pdContents : " + product.pdContents);
        success = postRepository.insertProductData(product);

        //알림생성



        return success;
    }

    @RequestMapping(value = "/image", method = {RequestMethod.POST})
    public String uploadProductImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String success = "false";
        String sellerId = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iterator = multipartRequest.getFileNames();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            for (MultipartFile file : multipartRequest.getFiles(key)) {
                System.out.println("file name : " + file.getName());
                System.out.println("file size : " + file.getSize());

                //이미지저장경로를 src/main/resources/static/images 로 해야된다.
                //http://(서버 아이피)/images/~~~.jpg로 들어갔을때 이미지가 보여야한다.
//                String savePath = "C:\\Users\\kddns\\Documents\\Longstone\\server\\Board\\src\\main\\resources\\static\\images\\" + file.getOriginalFilename();
//                String savePath = "\\home\\ec2-user\\Board\\src\\main\\resources\\static\\images\\" + file.getOriginalFilename();
                String savePath = "/home/ec2-user/Board/src/main/resources/static/images/" + file.getOriginalFilename();
                String dbSavePath = "http://ec2-3-35-255-89.ap-northeast-2.compute.amazonaws.com/images/" + file.getOriginalFilename();
//                String dbSavePath = "http://222.103.14.187:8080/images/" + file.getOriginalFilename();

                System.out.println("seller name : " + sellerId);
                System.out.println("save file path : " + savePath);

                ImageFile imagefile = new ImageFile();
                imagefile.setsellerId(file.getName());
                imagefile.setimgUrl(dbSavePath);

                file.transferTo(new File(savePath));
                success = postRepository.insertProductImage(imagefile);
            }
        }
        return success;
    }

    @RequestMapping (value = "/updatestatus", method = {RequestMethod.POST})
    public String updateFoodStatus(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        Post postData = objectMapper.readValue(messageBody, Post.class);
        success = sellerFoodRepository.updateFoodStatus(postData);

        return success;
    }

    //판매중인거 다 땡겨오기
    @RequestMapping(value = "/onsalepost", method = {RequestMethod.POST})
    public JSONObject getTodayFoodData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("postStatus : " + messageBody);
        List<Post> post = postRepository.getOnsalePost(messageBody);
        JSONObject data = new JSONObject();
        data.put("post", post);
        return data;
    }

    //판매자 자기가 올린 글
    @RequestMapping (value = "/storeFood", method = {RequestMethod.POST})
    public JSONObject getSellerFood(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("sellerId : " + messageBody);
        List<storeFood> resultList = sellerFoodRepository.selectStoreFoodData(messageBody);
        JSONObject data = new JSONObject();

        data.put("post", resultList);
        return data;
    }

    //등록 삭제
    @RequestMapping (value = "/deleteStoreFood", method = {RequestMethod.POST})
    public String deleteSellerFood(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String success = "false";
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        Store store = objectMapper.readValue(messageBody, Store.class);

        System.out.println("sellerId : " + store.getSellerId() + "\n" +
                            "pdname : " + store.getPdname());
        success = sellerFoodRepository.deleteStoreFoodData(store);

        return success;
    }

    @RequestMapping(value = "/orderHistory", method = {RequestMethod.POST})
    public JSONObject orderHistory(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("sellerId : " + messageBody);
        List<OrderHistory> orderHistories = orderHistoryRepository.getOrderHistory(messageBody);
        JSONObject data = new JSONObject();
        data.put("orderHistory", orderHistories);
        return data;
    }
}
