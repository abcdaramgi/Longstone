package com.example.applicationtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_store_detile.*

class StoreDetailActivity: AppCompatActivity() {
    lateinit var datas : StoreData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detile)

        datas = intent.getSerializableExtra("data") as StoreData

        Glide.with(this).load(datas.img).into(imageView6)
        textView12.text = datas.storename
    }
}