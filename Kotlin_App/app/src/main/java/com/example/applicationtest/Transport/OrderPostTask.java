package com.example.applicationtest.Transport;

import android.os.AsyncTask;
import android.util.Log;

import com.example.applicationtest.MainActivity;
import com.example.applicationtest.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OrderPostTask extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;

    @Override
    protected String doInBackground(String... strings) {
        try {
            String str;
            URL url = new URL("http://222.103.14.187:8080/post/order");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            String uid = MyApplication.prefs.getString("id", "0");

            JSONObject sendJson = new JSONObject();
            sendJson.put("uid", uid);
            sendJson.put("pid", strings[0]);
            sendJson.put("pname", strings[1]);
            sendJson.put("price", strings[2]);
            sendJson.put("pcount", strings[3]);

            osw.write(sendJson.toString());
            Log.d("value :", sendJson.toString());
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            } else {
                Log.i("통신결과 : ", conn.getResponseCode() + "에러");
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
