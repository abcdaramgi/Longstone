package com.example.applicationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        //소비자 이동
        button2.setOnClickListener({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
        //판매자 이동
        button.setOnClickListener({
            val intent = Intent(this, StMainActivity::class.java)
            startActivity(intent)
        })
    }
}