package com.example.applicationtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        //소비자 이동
        btn_login.setOnClickListener({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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
}