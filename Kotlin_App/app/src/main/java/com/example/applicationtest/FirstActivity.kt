package com.example.applicationtest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationtest.Transport.LoginTask
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_menu_view.*

class FirstActivity : AppCompatActivity() {
    private var login_id : EditText? = null
    private var login_pw : EditText? = null
    private var login_btn : Button? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        //소비자 이동
        btn_login.setOnClickListener({

            Log.d("Login", "login start...")
            var result = login()
            Log.d("result = ", result)
            if(result == "true"){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Log.d("Login", "login fail...")
            }

            //테스트시 이부분 주석풀고 위쪽 코드 주석달고 테스트
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        })
        //판매자 이동
        btn_store.setOnClickListener({
            val intent = Intent(this, StMainActivity::class.java)
            startActivity(intent)
        })
        //회원가입
        btn_register.setOnClickListener({
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        })
    }
    fun login() : String{
        var result = ""
        try{
            login_id = findViewById(R.id.edit_id)
            login_pw = findViewById(R.id.edit_pw)
            login_btn = findViewById(R.id.btn_login)

            val id = login_id!!.text.toString()
            val pw = login_pw!!.text.toString()

            Log.d("앱에서 보낸값", "$id, $pw")

            val task = LoginTask()
            result = task.execute(id,pw).get()

            Log.d("받은값", result)
            Log.d("Login", "login end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
        return result
    }
}