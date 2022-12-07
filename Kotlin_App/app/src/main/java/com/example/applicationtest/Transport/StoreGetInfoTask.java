package com.example.applicationtest.Transport;

import android.os.AsyncTask;
import android.util.Log;

import com.example.applicationtest.DTO.StoreDTO;
import com.example.applicationtest.Singleton.SellerSingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StoreGetInfoTask extends AsyncTask<String, Void, String>{
    String sendMsg, receiveMsg;
    String a = "sbial";
    boolean success;
    @Override
    protected String doInBackground(String... strings) {
        try{
            String str;
            URL url = new URL("http://10.0.2.2:8080/store/info");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //url 연결
            //content type json
            conn.setRequestProperty("Content-Type", "application/json");

            //보내는방식 GET OR POST
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다..
            //서버에 보낼값포함해 요청함
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

//            JSONObject sendJson = new JSONObject();
//            sendJson.put("sellerId", strings[0]);
            String sendData = strings[0];

//            osw.write(sendJson.toString());
            osw.write(sendData);
//            Log.d("value :", sendJson.toString());
            Log.d("value :", sendData);
            osw.flush();

            if(conn.getResponseCode() == conn.HTTP_OK){ //통신 ready?
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while((str = reader.readLine()) != null){
                    buffer.append(str);
                }

                receiveMsg = buffer.toString();
                receiveMsg = receiveMsg.toString();
                Log.d("receiveMsg", receiveMsg);

            }else{
                Log.i("통신결과 : ", conn.getResponseCode()+"에러");
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}
