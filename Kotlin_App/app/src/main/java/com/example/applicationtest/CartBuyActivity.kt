package com.example.applicationtest

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.applicationtest.Transport.OrderPostTask
import kotlinx.android.synthetic.main.activity_cart_buy.*
import kotlinx.android.synthetic.main.item_menu_view.*

class CartBuyActivity : AppCompatActivity() {
    lateinit var datas : ItemCart
    var num : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_buy)

        datas = intent.getSerializableExtra("buy") as ItemCart

        Glide.with(this).load(datas.food_img).into(buy_food_img)
        Glide.with(this).load(R.drawable.ic_baseline_home_24).into(img_store)
        buy_food_name.text = datas.FoodName
        buy_store_name.text = datas.StoreName
        buy_food_cost.text = datas.cost.toString() + "원"
        buy_num.text = datas.food_count.toString() + "개"
        textView28.text = (datas.cost?.times(datas.food_count!!)).toString() + "원"

        cart_buy.setOnClickListener({
            val builder = AlertDialog.Builder(this)
                .setMessage("구매 확인에 동의해주세요.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.show()
        })

        buy_check.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                cart_buy.setOnClickListener({
                    val intent = Intent(this, CartCheckBuyActivity::class.java)
                    intent.putExtra("data",datas)
                    startActivity(intent)
                })
            }
            else {
                cart_buy.setOnClickListener({
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