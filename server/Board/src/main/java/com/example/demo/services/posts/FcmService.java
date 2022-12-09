package com.example.demo.services.posts;

import com.example.demo.model.FcmMessage;
import com.example.demo.model.testMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.http.HttpHeaders;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Service
@RequiredArgsConstructor
public class FcmService {
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/goldenhour-cdb6e/messages:send";
    private final String URL = "https://fcm.googleapis.com/fcm/send";
    private final ObjectMapper objectMapper;
    private FirebaseMessaging instance;

    public void sendMessageTo(String targetToken, String title, String body) throws IOException {
        String message = makeMessage(targetToken, title, body);
        System.out.println(message);
        OkHttpClient client = new OkHttpClient();
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(message,
                okhttp3.MediaType.get("application/json; charset=utf-8"));
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "key=AAAA5LcZ2NA:APA91bH78hrbpmXnLggqftgkJAo3II0DyNIlgABja9S7nVWiYHI_k9nmBqj515mWmVIcdNzruXNW8FdRLUqqD4VqP3UW-nuJOI0xkCPGiomCz6QrJTliicMAHIfLwGdU9TqYNIszm6W7")
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        okhttp3.Response response = client.newCall(request).execute();

        System.out.println("sendmessage");
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonParseException, JsonProcessingException {
        testMessage fcmMessage = testMessage.builder()
                .data(testMessage.Message.builder()
                        .body(body)
                        .title(title)
                        .build())
                .priority("high").to(targetToken).build();
        System.out.println("makemessage");
        return objectMapper.writeValueAsString(fcmMessage);
    }

    public void sendTopicMessageTo(String topic, String title, String body) throws IOException{
        String message = makeTopicMessage(topic, title, body);
        System.out.println(message);
        OkHttpClient client = new OkHttpClient();
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(message,
                okhttp3.MediaType.get("application/json; charset=utf-8"));
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "key=AAAA5LcZ2NA:APA91bH78hrbpmXnLggqftgkJAo3II0DyNIlgABja9S7nVWiYHI_k9nmBqj515mWmVIcdNzruXNW8FdRLUqqD4VqP3UW-nuJOI0xkCPGiomCz6QrJTliicMAHIfLwGdU9TqYNIszm6W7")
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        okhttp3.Response response = client.newCall(request).execute();

        System.out.println("sendtopic");
    }

    public String makeTopicMessage(String topic, String title, String body) throws JsonProcessingException {

        testMessage fcmMessage = testMessage.builder()
                .data(testMessage.Message.builder()
                        .body(body)
                        .title(title)
                        .build())
                .priority("high").to("/topics/" + topic).build();

//        FcmMessage fcmMessage = FcmMessage.builder()
//                .message(FcmMessage.Message.builder()
//                        .topic("/topics/" + topic)
//                        .notification(FcmMessage.Notification.builder()
//                                .title(title)
//                                .body(body)
//                                .topic(topic)
//                                .build()
//                        ).build()).validateOnly(false).build();
//        System.out.println(fcmMessage.getMessage().getTopic());

        System.out.println(fcmMessage.getTo());
        System.out.println(fcmMessage.getData());
        System.out.println(fcmMessage.getData().getBody());
        System.out.println(fcmMessage.getData().getTitle());
        System.out.println("maketopic");


        return objectMapper.writeValueAsString(fcmMessage);
    }


    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase_services_key.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

//    public void sendTopicMessage(String topic, String title, String body) throws FirebaseMessagingException {
//        System.out.println("32q4erwg435!???");
//        this.sendTopicMessage(topic, title, body, null);
//    }

    public String sendMessage(Message message) throws FirebaseMessagingException {
        System.out.printf("ddasertasdfG??S?EDF???");
        return this.instance.send(message);
    }

//    @PostConstruct
//    public void firebaseSetting() throws IOException {
//        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("firebase_service_key.json").getInputStream())
//                .createScoped((Arrays.asList("dd")));
//
//        FirebaseOptions secondaryAppConfig = FirebaseOptions.builder()
//                .setCredentials(googleCredentials)
//                .build();
//        FirebaseApp app = FirebaseApp.initializeApp(secondaryAppConfig);
//        this.instance = FirebaseMessaging.getInstance(app);
//    }
//
//    public BatchResponse sendMessage(MulticastMessage message) throws FirebaseMessagingException {
//        return this.instance.sendMulticast(message);
//    }
}
