package com.example.applicationtest.Transport;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class deleteFoodTask extends AsyncTask<String, Void, String>{
    String sendMsg, receiveMsg;
    @Override
    protected String doInBackground(String... strings) {
        try{
            String str;
            URL url = new URL("http://222.103.14.187:8080/post/deleteStoreFood");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");

            //보내는방식
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            //서버에 보낼값포함해 요청함
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

            String sendData = strings[0];

            osw.write(sendData);
            Log.d("pdName :", sendData);
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
        }
        return receiveMsg;
    }
}
