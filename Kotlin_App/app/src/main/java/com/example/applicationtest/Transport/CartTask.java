package com.example.applicationtest.Transport;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CartTask extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;
    boolean success;
    @Override
    protected String doInBackground(String... strings) {
        try{
            String str;
            URL url = new URL("http://ec2-3-35-255-89.ap-northeast-2.compute.amazonaws.com/post/cart");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestProperty("Content-Type", "application/x-www-form-unlencoded");
            conn.setRequestProperty("Content-Type", "application/json");
//                application/json이 {key:value}의 형태로 전송되며
//                application/x-www-form-urlencoded가 key-value&key=value...의 형태로 전송

            //보내는방식 GET OR POST
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            //서버에 보낼값포함해 요청함
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            JSONObject sendJson = new JSONObject();
            sendJson.put("userId", strings[0]);
            sendJson.put("pdName", strings[1]);
            sendJson.put("storeName", strings[2]);
            sendJson.put("pdCount", strings[3]);

//                osw.write(sendMsg);
            osw.write(sendJson.toString());
            Log.d("value :", sendJson.toString());
            osw.flush();


            //통신도 잘되고 서버에서 보낸값 받음
            if(conn.getResponseCode() == conn.HTTP_OK){
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while((str = reader.readLine()) != null){
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            }else{
                Log.i("통신결과 : ", conn.getResponseCode()+"에러");
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}