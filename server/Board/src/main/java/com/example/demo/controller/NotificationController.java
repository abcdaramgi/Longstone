package com.example.demo.controller;

import com.example.demo.model.RequestDTO;
import com.example.demo.repository.SellerRepository;
import com.example.demo.services.posts.FcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final FcmService fcmService;

    @Autowired
    SellerRepository sellerRepository;

    //토픽
    @PostMapping(value = "/notification/topic/{topic}")
    public ResponseEntity notificationTopics(@PathVariable("topic") String topic, @RequestBody RequestDTO requestDTO) throws IOException {
        fcmService.sendTopicMessageTo(
                topic,
                requestDTO.getTitle(),
                requestDTO.getBody());
        return ResponseEntity.ok().build();
    }

    //유저
    @PostMapping("/notification/user")
    public ResponseEntity pushMessage(@RequestBody RequestDTO requestDTO) throws IOException {
        String uid = requestDTO.getId() + "님이";
        String pdid = requestDTO.getTitle();
        String count = requestDTO.getBody() + "개를 구매하셨습니다";
        String success = sellerRepository.selectSellerToken(pdid);

        fcmService.sendMessageTo(
                success,
                uid,
                count);

        return ResponseEntity.ok().build();
    }


    //특정고객
//    @PostMapping(value = "/notification/buy/{user}")
//    public void notificationUser(@PathVariable("no") Long no, @RequestBody RequestPushMessage data) throws FirebaseMessagingException {
//        Optional<CoreUser> user = coreUserService.findById(no);
//        if (user.isPresent()) {
//            CoreUser it = user.get();
//            Notification notification = Notification.builder().setTitle(data.getTitle()).setBody(data.getBody()).setImage(data.getImage()).build();
//            Message.Builder builder = Message.builder();
//            Optional.ofNullable(data.getData()).ifPresent(sit -> builder.putAllData(sit));
//            Message msg = builder.setToken(it.getPushToken()).setNotification(notification).build();
//            fcmService.sendMessage(msg);
//        }
//    }

    //토픽
//    @PostMapping(value = "/notification/topic/{topic}")
//    public void notificationTopics(@PathVariable("topic") String topic, @RequestBody RequestPushMessage data) throws FirebaseMessagingException {
//        com.google.firebase.messaging.Notification notification = com.google.firebase.messaging.Notification.builder().setTitle(data.getTitle()).setBody(data.getBody()).build();
//        com.google.firebase.messaging.Message.Builder builder = com.google.firebase.messaging.Message.builder();
//        //Optional.ofNullable(data.getData()).ifPresent(sit -> builder.putAllData(sit));
//        System.out.println(topic);
//        com.google.firebase.messaging.Message msg = builder.setTopic(topic).setNotification(notification).build();
//        fcmService.sendMessage(msg);
//    }


}
