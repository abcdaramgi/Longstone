/*package com.example.demo.services.posts;

import com.example.demo.model.FcmMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
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

import java.io.IOException;
import java.util.List;

@Component
@Service
@RequiredArgsConstructor
public class FcmService {
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/goldenhour-cdb6e/messages:send";
    private final ObjectMapper objectMapper;
    private FirebaseMessaging instance;

    public void sendMessageTo(String targetToken, String title, String body) throws IOException {
        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = (RequestBody) okhttp3.RequestBody.create(message,
                okhttp3.MediaType.get("application/json; charset=utf-8"));
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(API_URL)
                .post((okhttp3.RequestBody) requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        okhttp3.Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonParseException, JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        ).build()).validateOnly(false).build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "resources/firebase_service_key.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    public void sendTopicMessage(String topic, String title, String body) throws FirebaseMessagingException {
        System.out.println("32q4erwg435!???");
        this.sendTopicMessage(topic, title, body, null);
    }

    public void sendTopicMessage(String topic, String title, String body, String image) throws FirebaseMessagingException {
        Notification notification = Notification.builder().setTitle(title).setBody(body).setImage(image).build();
        Message msg = Message.builder().setTopic(topic).setNotification(notification).build();
        System.out.println("qqwe1212!???");
        sendMessage(msg);
    }

    public String sendMessage(Message message) throws FirebaseMessagingException {
        System.out.printf("ddasertasdfG??S?EDF???");
        return this.instance.send(message);
    }
}*/
