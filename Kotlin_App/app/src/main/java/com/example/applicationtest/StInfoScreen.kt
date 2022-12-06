package com.example.applicationtest

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.applicationtest.DTO.StoreDTO
import com.example.applicationtest.Singleton.SellerSingleton
import com.example.applicationtest.Transport.StoreGetImgTask
import com.example.applicationtest.Transport.StoreGetInfoTask
import kotlinx.android.synthetic.main.activity_st_info_screen.*
import kotlinx.android.synthetic.main.activity_st_review_screen.*
import org.json.JSONArray
import org.json.JSONObject

class StInfoScreen : AppCompatActivity() {
    private var store_img : ImageView? = null;
    private var store_name : TextView? = null;
    private var store_phoneNum : TextView? = null;
    private var store_addr : TextView? = null;
    private var store_openHour : TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st_info_screen)
        setSupportActionBar(st_toolbar_stinfo) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시

        //============================================================//
        // 가게정보 불러오기
        //============================================================//
        store_img = findViewById(R.id.store_img)
        store_name = findViewById(R.id.st_de_name)
        store_phoneNum = findViewById(R.id.textView_st_phonenum)
        store_addr = findViewById(R.id.textView_st_address)
        store_openHour = findViewById(R.id.textView_st_openingtime)
        //데이터 불러오기 함수호출
        getStoreData()
        getStoreImgData()
        //============================================================//
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            //뒤로가기 버튼이 눌리면 현재 접속 중인 화면을 나감
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getStoreData(){
        var result = ""
        try{
            Log.d("GET STORE DATA", "GET DATA start...")

            val sellerId = SellerSingleton.getInstance().sellerId
            Log.d("앱에서 보낸값", sellerId)

            val task = StoreGetInfoTask()
            result = task.execute(sellerId).get()
            Log.d("받은값", result)

            if(result != null){
                val `object` = JSONObject(result)
                val array = `object`.get("store") as JSONArray

                val row = array.getJSONObject(0)
                val storeDTO = StoreDTO()

                storeDTO.setName(row.getString("name"))
                storeDTO.setNumber(row.getString("number"))
                storeDTO.setOpenHour(row.getString("openHour"))
                storeDTO.setStoreAddr(row.getString("storeAddr"))

                Log.d("storeName : ", storeDTO.getName())
                Log.d("storeNum : ", storeDTO.getNumber())
                Log.d("openHour : ", storeDTO.getOpenHour())
                Log.d("storeAddr : ", storeDTO.getStoreAddr())

                store_name!!.setText(storeDTO.getName());
                store_phoneNum!!.setText(storeDTO.getNumber());
                store_addr!!.setText(storeDTO.getStoreAddr());
                store_openHour!!.setText(storeDTO.getOpenHour());
            }
            else{
                Log.d("GET STORE DATA", "GET DATA FAIL...")
            }
            Log.d("GET STORE DATA", "GET DATA end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
    }

    fun getStoreImgData(){
        var result = ""
        try{
            Log.d("GET STORE IMG DATA", "GET DATA IMG start...")

            val sellerId = SellerSingleton.getInstance().sellerId
            Log.d("앱에서 보낸값", sellerId)

            val task = StoreGetImgTask()
            result = task.execute(sellerId).get()

            if(result != null){
//                store_img!!.setImageBitmap(result);
                //imageView 에 append
                Glide.with(this).load(result).into(store_img!!)
            }
            else{
                Log.d("GET STORE IMG DATA", "GET DATA IMG FAIL...")
            }
            Log.d("GET STORE IMG DATA", "GET DATA IMG end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
    }
}