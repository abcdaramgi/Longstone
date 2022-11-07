package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.DTO.MemberDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Runnable{

    private Button load_btn;
    private Button send_btn;
    private EditText name_edit;
    private EditText email_edit;
    ListView list;
    List<MemberDTO> items;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼, list를 받아온다
        load_btn = findViewById(R.id.load_btn);
        list = findViewById(R.id.list);
        send_btn = findViewById(R.id.send_btn);
        name_edit = findViewById(R.id.name_edit);
        email_edit = findViewById(R.id.email_edit);
        items = new ArrayList<>();

        //백그라운드 스레드
        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread th = new Thread(MainActivity.this);
                th.start();
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("hello", "test test test");
                login();
            }
        });
    }
    void login(){
        Log.w("login", "로그인하는중");
        try{
            String name = name_edit.getText().toString();
            String email = email_edit.getText().toString();
            Log.w("보낸값 : ", name+", "+email);

            CustomTask task = new CustomTask();
            String result = task.execute(name,email).get();

            Log.w("받은값", result);
        }catch (Exception e){

        }
    }
    class CustomTask extends AsyncTask<String, Void, String>{
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try{
                String str;
                URL url = new URL("http://222.103.14.179:8080/android");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pw="+strings[1]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
                osw.write(sendMsg);                           // OutputStreamWriter에 담아 전송
                osw.flush();

                // jsp와 통신이 잘 되고, 서버에서 보낸 값 받음.
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {    // 통신이 실패한 이유를 찍기위한 로그
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 서버에서 보낸 값을 리턴합니다.
            return receiveMsg;
        }
    }
    //Handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            String[] str = new String[items.size()];
            for(int i = 0; i < str.length; i++){
                MemberDTO dto = items.get(i);
                str[i]=(i+1)+". " + dto.getName() + " [ " + dto.getEmail() + " ] ";
            }
            //안드로이드가 미리 만들어놓은 simple_list_items_1 레이아웃으로 어뎁터 생성
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, str);
            list.setAdapter(adapter);
        }
    };

    @Override
    public void run() {
        try{
            StringBuffer sb = new StringBuffer();
            URL url = new URL("http://222.103.14.179:8080/json.do");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if(conn != null){
                conn.setConnectTimeout(5000);
                conn.setUseCaches(false);

                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                    while (true){
                        String line = br.readLine();
                        if(line == null)
                            break;
                        sb.append(line + "\n");
                    }
                    Log.d("myLog", sb.toString());
                    br.close();
                }
                conn.disconnect();
            }
            //받아온 data를 JSONObject로 변환
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("member");

            //0번째 JSONObject 받아옴
            JSONObject row = jsonArray.getJSONObject(0);
            MemberDTO dto = new MemberDTO();
            dto.setName(row.getString("name"));
            dto.setEmail(row.getString("email"));
            items.add(dto);

            Log.d("받아온값1 : ", row.getString("name"));
            Log.d("받아온값2 : ", row.getString("email"));

            // 1번째 JSONObject를 받아옴
            JSONObject row2 = jsonArray.getJSONObject(1);
            MemberDTO dto2 = new MemberDTO();
            dto2.setName(row2.getString("name"));
            dto2.setEmail(row2.getString("email"));
            items.add(dto2);

            Log.d("받아온값3 : ", row2.getString("name"));
            Log.d("받아온값4 : ", row2.getString("email"));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error", e.getMessage());
        }
        handler.sendEmptyMessage(0);
    }
}