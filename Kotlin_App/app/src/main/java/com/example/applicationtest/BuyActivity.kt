package com.example.applicationtest

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_buy.*
import kotlinx.android.synthetic.main.item_today_food_detail.*

class BuyActivity : AppCompatActivity() {
    lateinit var datas : FoodData
    var num : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        datas = intent.getSerializableExtra("data") as FoodData
        num = intent.getSerializableExtra("foodCount") as Int

        Glide.with(this).load(datas.img).into(buy_food_img)
        Glide.with(this).load(datas.storeimg).into(img_store)
        buy_food_name.text = datas.name
        buy_store_name.text = datas.storename
        buy_store_place.text = datas.place
        buy_food_cost.text = datas.updatecost.toString() + "원"
        buy_count.text = num.toString()

        button4.setOnClickListener({
            val builder = AlertDialog.Builder(this)
                .setMessage("구매 확인에 동의해주세요.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.show()
        })

        buy_check.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                button4.setOnClickListener({
                    val intent = Intent(this, CheckBuyActivity::class.java)
                    intent.putExtra("foodCount",num)
                    intent.putExtra("data",datas)
                    startActivity(intent)
                })
            }
            else {
                button4.setOnClickListener({
                    val builder = AlertDialog.Builder(this)
                        .setMessage("구매 확인에 동의해주세요.")
                        .setPositiveButton("확인",
                            DialogInterface.OnClickListener { dialog, id ->
                            })
                    builder.show()
                })
            }
        }
    }
}