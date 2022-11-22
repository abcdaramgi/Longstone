package com.example.applicationtest

import android.content.Intent
import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_alertdialog.view.*
import kotlinx.android.synthetic.main.item_today_food_detail.*

// 메인 화면 상품 리스트를 클릭했을 때, 상품의 정보를 자세히 보여주는 화면
class FoodDetailActivity : AppCompatActivity() {
    lateinit var datas : FoodData
    var ad_count : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_today_food_detail)

        datas = intent.getSerializableExtra("data") as FoodData

        Glide.with(this).load(datas.img).into(img_profile)
        detail_food_name.text = datas.name
        ad_count = datas.count!!
        store_de_name.text = datas.storename

        var selectNum = 0

        // 구매 버튼을 눌렀을 때, 구매창(BuyActivity)으로 넘어감
        buy_bnt.setOnClickListener({
                val layout = layoutInflater.inflate(R.layout.item_alertdialog, null)
                val build = AlertDialog.Builder(it.context).apply {
                    setView(layout)
                }
                val dialog = build.create()
                dialog.show()

                layout.number_picker.minValue = 1
                layout.number_picker.maxValue = ad_count
                if (selectNum != 0) layout.number_picker.value = selectNum

                layout.btn_no.setOnClickListener {
                    dialog.dismiss()
                }

                layout.btn_ok.setOnClickListener {
                    selectNum = layout.number_picker.value
                    val intent = Intent(this, BuyActivity::class.java)
                    startActivity(intent)
                }
            })

        // 가게 정보가 담긴 부분을 눌렀을 때, 가게 상세 정보(FoodStoreDetailActivity) 창으로 넘어감
        store_box.setOnClickListener({
            val intent = Intent(this, FoodStoreDetailActivity::class.java)
            startActivity(intent)
        })

        review_img.setOnClickListener({
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        })

        button3.setOnClickListener({
            val layout = layoutInflater.inflate(R.layout.item_alertdialog, null)
            val build = AlertDialog.Builder(it.context).apply {
                setView(layout)
            }
            val dialog = build.create()
            dialog.show()

            layout.number_picker.minValue = 1
            layout.number_picker.maxValue = ad_count
            if (selectNum != 0) layout.number_picker.value = selectNum

            layout.btn_no.setOnClickListener {
                dialog.dismiss()
            }

            layout.btn_ok.setOnClickListener {
                selectNum = layout.number_picker.value
            }
        })
    }
}
