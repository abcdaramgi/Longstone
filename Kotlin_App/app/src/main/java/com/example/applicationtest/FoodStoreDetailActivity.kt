package com.example.applicationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_food_store_detail.*
import kotlinx.android.synthetic.main.activity_store_detile.*
import kotlinx.android.synthetic.main.activity_store_detile.img_prefer
import kotlinx.android.synthetic.main.activity_store_detile.st_de_info
import kotlinx.android.synthetic.main.activity_store_detile.st_de_name
import kotlinx.android.synthetic.main.activity_store_detile.store_img
import kotlinx.android.synthetic.main.fragment_cart_screen.*

class FoodStoreDetailActivity : AppCompatActivity() {
    lateinit var data : FoodData
    lateinit var storeFoodListAdapter: StoreFoodListAdapter
    val datas = mutableListOf<ItemStoreFood>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_store_detail)

        data = intent.getSerializableExtra("data") as FoodData

        initRecycler()
        store_re.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        Glide.with(this).load(data.img).into(store_img)
        st_de_name.text = data.storename
        st_de_info.text = data.place

        img_prefer.setOnClickListener{
            var storeData = StoreData()
            storeData.storename = data.storename
            storeData.storeinfo = data.place
            storeData.img = data.img

            var intent = Intent(this, FoodDetailActivity::class.java)
            intent.putExtra("new_store", storeData)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun initRecycler() {
        storeFoodListAdapter = StoreFoodListAdapter(this)
        store_re.adapter = storeFoodListAdapter

        datas.apply {
            add(ItemStoreFood("생크림 소금빵",R.drawable.image_bread1,3000,1500))
            add(ItemStoreFood("양념불고기(간장)",R.drawable.image_bread1,8000,4500))
            add(ItemStoreFood("김혜르무르트 2세 쿠키",R.drawable.image_bread1,1200, 800))
        }
        storeFoodListAdapter.datas = datas
        storeFoodListAdapter.notifyDataSetChanged()
    }
}