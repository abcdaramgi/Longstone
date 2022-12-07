package com.example.applicationtest

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.applicationtest.DTO.FoodData
import com.example.applicationtest.Transport.OrderPostTask
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
        buy_count.text = num.toString() + "개"
        food_buy_cost.text = (datas.updatecost?.times(num)).toString() + "원"

        button4.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setMessage("구매 확인에 동의해주세요.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.show()
        }

        buy_check.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                button4.setOnClickListener {
                    val intent = Intent(this, CheckBuyActivity::class.java)
                    intent.putExtra("foodCount", num)
                    intent.putExtra("data", datas)

                    val task = OrderPostTask()
                    val result = task.execute("1", datas.name, (datas.updatecost?.times(num)).toString(), num.toString()).get()


                    //Log.d("받은값", result)

                    startActivity(intent)
                }
            }
            else {
                button4.setOnClickListener {
                    val builder = AlertDialog.Builder(this)
                        .setMessage("구매 확인에 동의해주세요.")
                        .setPositiveButton("확인",
                            DialogInterface.OnClickListener { dialog, id ->
                            })
                    builder.show()
                }
            }
        }
    }
}