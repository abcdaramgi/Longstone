package com.example.applicationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.applicationtest.DTO.FoodData
import com.example.applicationtest.DTO.StoreDTO
import com.example.applicationtest.FireBase.MyFirebaseMessagingService
import com.example.applicationtest.Singleton.SellerSingleton
import com.example.applicationtest.Singleton.UserSingleton
import com.example.applicationtest.Transport.FavoritesTask
import com.example.applicationtest.Transport.SellerLoginTask
import com.example.applicationtest.Transport.StoreDetailListTask
import com.example.applicationtest.Transport.StoreGetImgTask2
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_food_store_detail.*
import kotlinx.android.synthetic.main.activity_store_detile.*
import kotlinx.android.synthetic.main.activity_store_detile.img_prefer
import kotlinx.android.synthetic.main.activity_store_detile.st_de_info
import kotlinx.android.synthetic.main.activity_store_detile.st_de_name
import kotlinx.android.synthetic.main.activity_store_detile.store_img
import kotlinx.android.synthetic.main.fragment_cart_screen.*
import org.json.JSONArray
import org.json.JSONObject

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
        //======================================================//
//        val task = StoreGetImgTask2();
//        val resultImg = task.execute(data.getStorename().toString()).get()
//
//        data.setImg(resultImg)
//        data.setStoreimg(resultImg)
        //======================================================//
        Glide.with(this).load(data.img).into(store_img)
        st_de_name.text = data.storename
        st_de_info.text = data.place

        //???????????? (?????????)
        img_prefer.setOnClickListener{
            var storeData = StoreData()
            storeData.storename = data.storename
            storeData.storeinfo = data.place
            storeData.img = data.img.toString()

            //===================================================================//
            //???????????? ?????? ??????
            //===================================================================//
            Log.d("Favorites", "Favorites start...")
            val result = setFavorites()
            Log.d("result = ", result)
            if(result == "true"){
                //?????? ???????????? ???????????? ???????????? ????????? ????????????.
                //????????? ???????????? ??? ????????? => ??????????????? ????????? ??????????????? ??????
            }else{
                Log.d("Favorites", "Favorites fail...")
            }
            //===================================================================//

            //????????????
            var intent = Intent(this, FoodDetailActivity::class.java)
            intent.putExtra("new_store", storeData)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun initRecycler() {
        storeFoodListAdapter = StoreFoodListAdapter(this)
        store_re.adapter = storeFoodListAdapter

        //===================================================//
        datas!!.clear()
        var salepercent = 0.0;
        var orgPrice = 0.0;
        var salePrice = 0;
        var result = ""
        try{
            Log.d("Store Detail List", "Store List start...")
            val storeName = data.storename.toString()
            Log.d("????????? ?????????", "$storeName")

            val task = StoreDetailListTask();
            result = task.execute(storeName).get()
            Log.d("?????????", result)

            if(result != null) {
                val `object` = JSONObject(result)
                val array = `object`.get("StoreDetailList") as JSONArray

                for (i: Int in 0..array.length() - 1) {
                    var row = array.getJSONObject(i)
                    var storeDTO = StoreDTO()

                    storeDTO.setPdname(row.getString("pdname"))
                    storeDTO.setImgUrl(row.getString("imgUrl"))
                    storeDTO.setpdPrice(row.getInt("pdPrice"))
                    storeDTO.setpdSale(row.getInt("pdSale"))

                    Log.d("pdName : ", storeDTO.getPdname())
                    Log.d("imgUrl : ", storeDTO.getImgUrl())
                    Log.d("pdPrice : ", storeDTO.getpdPrice().toString())
                    Log.d("pdSale : ", storeDTO.getpdSale().toString())
                    salepercent = storeDTO.getpdSale().toDouble();
                    orgPrice = storeDTO.getpdPrice().toDouble();
                    Log.d("salepercent", salepercent.toString())
                    Log.d("orgPrice", orgPrice.toString())
                    salePrice = (orgPrice - (orgPrice * (salepercent / 100))).toInt()
                    Log.d("salePrice", salePrice.toString())
                    Log.d("sibal", (orgPrice * (salepercent / 100)).toString())
                    Log.d("sib2", (orgPrice - (orgPrice * (salepercent / 100))).toString())
                    datas.add(
                        ItemStoreFood(
                            storeDTO.getPdname(), storeDTO.getImgUrl(), storeDTO.getpdPrice(), salePrice))
                }
            }
            else{
                Log.d("Store Detail List", "Store List fail...")
            }
            Log.d("Store Detail List", "Store List end...")
        }catch (e :Exception) {
            e.printStackTrace()
        }
        //===================================================//

        datas.apply {
//            add(ItemStoreFood("????????? ?????????",R.drawable.image_bread1,3000,1500))
//            add(ItemStoreFood("???????????????(??????)",R.drawable.image_bread1,8000,4500))
//            add(ItemStoreFood("?????????????????? 2??? ??????",R.drawable.image_bread1,1200, 800))
        }
        storeFoodListAdapter.datas = datas
        storeFoodListAdapter.notifyDataSetChanged()
    }

    fun setFavorites(): String {
        var result = ""
        val sellerId = data.getSellerId()
        try{
            val userId = UserSingleton.getInstance().userId.toString();
            val storeName = data.storename.toString();

            Log.d("????????? ?????????", "$userId, $storeName")

            val task = FavoritesTask()
            result = task.execute(userId, storeName).get()

            Log.d("?????????", result)

            Log.d("Favorites", "Favorites end...")
        }catch(e : Exception){
            e.printStackTrace()
        }

        FirebaseMessaging.getInstance().subscribeToTopic(sellerId.toString()).addOnCompleteListener { task ->
            var msg = "Subscribed"
            if (!task.isSuccessful) {
                msg = "Subscribe failed"
            }
            Log.d(MyFirebaseMessagingService.TAG, msg!!)
            Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
        }

        return result
    }
}