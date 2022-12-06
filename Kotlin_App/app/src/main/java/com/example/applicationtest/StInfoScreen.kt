package com.example.applicationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.applicationtest.Singleton.SellerSingleton
import com.example.applicationtest.Singleton.UserSingleton
import com.example.applicationtest.Transport.LoginTask
import com.example.applicationtest.Transport.StoreGetInfoTask
import kotlinx.android.synthetic.main.activity_st_info_screen.*
import kotlinx.android.synthetic.main.activity_st_review_screen.*

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
        Log.d("GET STORE DATA", "GET DATA start...")
        getStoreData()
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

            val strName = store_name!!.text.toString()
            val strPhone = store_phoneNum!!.text.toString()
            val strAddr = store_addr!!.text.toString()
            val strOpenHour = store_openHour!!.text.toString()

            val sellerId = SellerSingleton.getInstance().sellerId
            Log.d("앱에서 보낸값", sellerId)

            val task = StoreGetInfoTask()

            result = task.execute(sellerId).get()

            Log.d("받은값", result)

            if(result == "true"){

            }
            Log.d("GET STORE DATA", "GET DATA end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
    }
}