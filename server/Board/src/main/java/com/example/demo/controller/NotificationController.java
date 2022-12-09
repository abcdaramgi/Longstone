/*package com.example.demo.controller;

import com.example.demo.model.FcmMessage;
import com.example.demo.model.RequestPushMessage;
import com.example.demo.services.posts.FcmService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Notification;
import java.util.Optional;

@RestController
@RequestMapping(path="/notification")
public class NotificationController {

    @Autowired
    FcmService fcmService;

    //토픽
    @PostMapping(value = "/notification/topic/{topic}")
    public void notificationTopics(@PathVariable("topic") String topic, @RequestBody RequestPushMessage data) throws FirebaseMessagingException {
        com.google.firebase.messaging.Notification notification = com.google.firebase.messaging.Notification.builder().setTitle(data.getTitle()).setBody(data.getBody()).build();
        com.google.firebase.messaging.Message.Builder builder = com.google.firebase.messaging.Message.builder();
        //Optional.ofNullable(data.getData()).ifPresent(sit -> builder.putAllData(sit));
        System.out.println(topic);
        com.google.firebase.messaging.Message msg = builder.setTopic(topic).setNotification(notification).build();
        fcmService.sendMessage(msg);
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


}*/
