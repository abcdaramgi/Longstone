package com.example.applicationtest.Transport;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.applicationtest.Singleton.SellerSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StoreGetImgTask extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;
    Bitmap bitmap = null;
    @Override
    protected String doInBackground(String... Strings) {
        try{
            String str;
            URL url = new URL("http://10.0.2.2:8080/store/imginfo");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");

            //보내는방식
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            //서버에 보낼값포함해 요청함
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            String sendData = Strings[0];

            osw.write(sendData);
            Log.d("value :", sendData);
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