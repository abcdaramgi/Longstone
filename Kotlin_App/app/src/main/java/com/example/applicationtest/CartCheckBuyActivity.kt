package com.example.applicationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_check_buy.*
import kotlinx.android.synthetic.main.activity_check_buy.toolbar
import kotlinx.android.synthetic.main.fragment_bell_screen.*

class CartCheckBuyActivity : AppCompatActivity() {
    lateinit var datas : ItemCart
    var num : Int = 0
    var sum : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_check_buy)

        datas = intent.getSerializableExtra("data") as ItemCart

        store_name.text = datas.StoreName
        check_food_name.text = datas.FoodName
        buy_count.text = datas.food_count.toString() + "개"
        sum_cost.text = (datas.cost?.times(datas.food_count!!)).toString() + "원"

        setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
