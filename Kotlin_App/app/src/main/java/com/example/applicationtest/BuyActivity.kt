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
import com.example.applicationtest.DTO.StoreDTO
import com.example.applicationtest.Singleton.SellerSingleton
import com.example.applicationtest.Singleton.UserSingleton
import com.example.applicationtest.Transport.BuyNotificationTask
import com.example.applicationtest.Transport.NotificationTask
import com.example.applicationtest.Transport.OrderPostTask
import com.example.applicationtest.Transport.StoreGetInfoTask
import kotlinx.android.synthetic.main.activity_buy.*
import kotlinx.android.synthetic.main.activity_buy.buy_check
import kotlinx.android.synthetic.main.activity_buy.buy_food_cost
import kotlinx.android.synthetic.main.activity_buy.buy_food_img
import kotlinx.android.synthetic.main.activity_buy.buy_food_name
import kotlinx.android.synthetic.main.activity_buy.buy_store_name
import kotlinx.android.synthetic.main.activity_buy.img_store
import kotlinx.android.synthetic.main.activity_buy.user_de_place
import kotlinx.android.synthetic.main.activity_cart_buy.*
import kotlinx.android.synthetic.main.item_today_food_detail.*
import org.json.JSONArray
import org.json.JSONObject

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
        buy_count.text = num.toString() + "???"
        buy_food_cost.text = datas.cost.toString() + "???->" + (datas.updatecost).toString() + "???"
        food_buy_cost.text = (datas.updatecost?.times(num)).toString() + "???"
        user_de_place.text = datas.phoneNum.toString()

        button4.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setMessage("?????? ????????? ??????????????????.")
                .setPositiveButton("??????",
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
                    val result = task.execute(datas.pdId.toString(), datas.name, datas.updatecost.toString(), num.toString()).get()
                    Log.d("?????????", result)

                    //??????????????? ??????
                    sendNotification(datas)

                    startActivity(intent)
                }
            }
            else {
                button4.setOnClickListener {
                    val builder = AlertDialog.Builder(this)
                        .setMessage("?????? ????????? ??????????????????.")
                        .setPositiveButton("??????",
                            DialogInterface.OnClickListener { dialog, id ->
                            })
                    builder.show()
                }
            }
        }
    }

    private fun sendNotification(data:FoodData) {
        try {
            Log.d("Notification", "Notification start...")
            val task = BuyNotificationTask()
            val result = task.execute(UserSingleton.getInstance().userId, data.getPdId().toString(), data.getCount().toString()).get()
            Log.d("?????????", result)


            Log.d("post", "posting end...")
        } catch (e: Exception) {
        }
    }
}