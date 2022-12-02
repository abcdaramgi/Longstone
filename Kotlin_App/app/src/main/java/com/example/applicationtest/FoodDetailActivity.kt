package com.example.applicationtest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.applicationtest.MapScreen.Companion.newInstance
import kotlinx.android.synthetic.main.activity_buy.*
import kotlinx.android.synthetic.main.fragment_cart_screen.*
import kotlinx.android.synthetic.main.fragment_cart_screen.view.*
import kotlinx.android.synthetic.main.fragment_prefer_screen.*
import kotlinx.android.synthetic.main.item_alertdialog.view.*
import kotlinx.android.synthetic.main.item_today_food_detail.*
import kotlinx.android.synthetic.main.item_today_food_detail.store_box
import kotlinx.android.synthetic.main.item_today_food_detail.store_de_name
import kotlinx.android.synthetic.main.item_today_food_detail.store_de_place

// 메인 화면 상품 리스트를 클릭했을 때, 상품의 정보를 자세히 보여주는 화면
class FoodDetailActivity : AppCompatActivity() {
    lateinit var datas : FoodData
    lateinit var cartAdapter: CartAdapter
    lateinit var cart : ItemCart
    var ad_count : Int = 0
    var selectNum : Int = 0
    val NEW_STORE = 22
    val list : java.util.ArrayList<StoreData> = MainActivity().storeList as ArrayList
    lateinit var storeAdapter : StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_today_food_detail)

        val fragment: PreferScreen? =
            supportFragmentManager.findFragmentByTag("fragment_prefer_screen") as PreferScreen?
        supportFragmentManager.executePendingTransactions()

        fragment?.storeAdapter = StoreAdapter(list)
        /*fragment.st_listView.layoutManager = LinearLayoutManager(fragment.activity, RecyclerView.VERTICAL, false)
        fragment.st_listView.adapter = fragment.storeAdapter

        list.add(StoreData("hi","hell",R.drawable.image_bread1))
        storeAdapter.notifyDataSetChanged()*/

        datas = intent.getSerializableExtra("data") as FoodData

        Glide.with(this).load(datas.img).into(img_profile)
        Glide.with(this).load(datas.storeimg).into(img_store_photo)
        detail_food_name.text = datas.name
        ad_count = datas.count!!
        store_de_name.text = datas.storename
        store_de_place.text = datas.place
        review.text = datas.refood.toString() + "개"

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
                intent.putExtra("foodCount", selectNum)
                intent.putExtra("data", datas)
                startActivity(intent)
            }
        })

        // 가게 정보가 담긴 부분을 눌렀을 때, 가게 상세 정보(FoodStoreDetailActivity) 창으로 넘어감
        store_box.setOnClickListener({
            val intent = Intent(this, FoodStoreDetailActivity::class.java)
            intent.putExtra("data", datas)
            startActivityForResult(intent, NEW_STORE)
        })

        review_img.setOnClickListener({
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        })

        review.setOnClickListener({
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        })

        button3.setOnClickListener{
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
                var view : View = View.inflate(this,R.layout.fragment_cart_screen, null)
                selectNum = layout.number_picker.value
                //StoreData(data.storename, data.storeinfo, data.img)

                var cart = ItemCart()
                cart.StoreName= datas.storename
                cart.FoodName = datas.name
                cart.food_img = datas.img
                cart.food_count = selectNum

                cartAdapter = CartAdapter(this)
                view.re_cart.adapter = cartAdapter

                CartScreen().datas.add(cart)
                cartAdapter.notifyDataSetChanged()

                var intent = Intent(this, CartScreen::class.java)
                intent.putExtra("new_food", cart)
                setResult(RESULT_OK, intent)
                startActivity(intent)
            }
        }
    }
}
