package com.example.applicationtest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_store_detile.*
import kotlinx.android.synthetic.main.fragment_bell_screen.*
import kotlinx.android.synthetic.main.fragment_prefer_screen.*
import kotlinx.android.synthetic.main.item_today_food_detail.*

class StoreDetailActivity: AppCompatActivity() {
    lateinit var datas : StoreData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detile)

        datas = intent.getSerializableExtra("data") as StoreData

        Glide.with(this).load(datas.img).into(store_img)
        st_de_name.text = datas.storename

        img_prefer.setOnClickListener(
            {}
        )
    }
}