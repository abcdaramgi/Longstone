package com.example.applicationtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_today_food_detail.*

class FoodDetailActivity : AppCompatActivity() {
    lateinit var datas : FoodData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_today_food_detail)

        datas = intent.getSerializableExtra("data") as FoodData

        Glide.with(this).load(datas.img).into(img_profile)
        detail_food_name.text = datas.name

        buy_bnt.setOnClickListener({
            val intent = Intent(this, BuyActivity::class.java)
            startActivity(intent)
        })
    }
}