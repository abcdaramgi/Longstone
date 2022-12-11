package com.example.applicationtest

import android.app.Activity
import android.content.Intent
import android.content.IntentSender.OnFinished
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.applicationtest.DTO.FoodData
import com.example.applicationtest.MapScreen.Companion.newInstance
import com.example.applicationtest.Singleton.UserSingleton
import com.example.applicationtest.Transport.CartTask
import com.example.applicationtest.Transport.StoreGetImgTask2
import kotlinx.android.synthetic.main.activity_buy.*
import kotlinx.android.synthetic.main.fragment_cart_screen.*
import kotlinx.android.synthetic.main.fragment_cart_screen.view.*
import kotlinx.android.synthetic.main.fragment_prefer_screen.*
import kotlinx.android.synthetic.main.item_alertdialog.*
import kotlinx.android.synthetic.main.item_alertdialog.view.*
import kotlinx.android.synthetic.main.item_today_food_detail.*
import kotlinx.android.synthetic.main.item_today_food_detail.store_box
import kotlinx.android.synthetic.main.item_today_food_detail.store_de_name
import kotlinx.android.synthetic.main.item_today_food_detail.store_de_place
import okhttp3.internal.format
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

// 메인 화면 상품 리스트를 클릭했을 때, 상품의 정보를 자세히 보여주는 화면
class FoodDetailActivity : AppCompatActivity() {
    lateinit var datas : FoodData
    lateinit var cartAdapter: CartAdapter
    lateinit var countDownTimer: CountDownTimer
    lateinit var cart : ItemCart
    var ad_count : Int = 0
    var selectNum : Int = 0
    val NEW_STORE = 22
    val list : java.util.ArrayList<StoreData> = MainActivity().storeList as ArrayList

    var started = false
    var set = 0
    var total : Int? = null

    val handler = object: Handler(){
        override fun handleMessage(msg: Message) {
            val total = msg.what
            textView21.text = formatTime(total)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_today_food_detail)

        datas = intent.getSerializableExtra("data") as FoodData

        val now = LocalDateTime.now() // 현재 시간
        val time = datas.getExpire() // 변환할 문자열
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") //포맷

        val convertTime = LocalDateTime.parse(time, formatter)

        val compareTime = ChronoUnit.SECONDS.between(now, convertTime) //분단위 비교

        total = compareTime.toInt()

        val fragment: PreferScreen? =
            supportFragmentManager.findFragmentByTag("fragment_prefer_screen") as PreferScreen?
        supportFragmentManager.executePendingTransactions()

        start()

        fragment?.storeAdapter = StoreAdapter(list)


        /*fragment.st_listView.layoutManager = LinearLayoutManager(fragment.activity, RecyclerView.VERTICAL, false)
        fragment.st_listView.adapter = fragment.storeAdapter

        list.add(StoreData("hi","hell",R.drawable.image_bread1))
        storeAdapter.notifyDataSetChanged()*/
        //======================================================//
        val task = StoreGetImgTask2();
        val resultImg = task.execute(datas.getStorename().toString()).get()

        datas.setImg(resultImg)
        datas.setStoreimg(resultImg)
        //======================================================//
        Glide.with(this).load(datas.img).into(img_profile)
        Glide.with(this).load(datas.storeimg).into(img_store_photo)
        detail_food_name.text = datas.name
        ad_count = datas.count!!
        store_de_name.text = datas.getStorename()
        store_de_place.text = datas.place
        review.text = datas.refood.toString() + "개"
        textView20.text = datas.getPdContents()

        // 구매 버튼을 눌렀을 때, 구매창(BuyActivity)으로 넘어감
        buy_bnt.setOnClickListener {
            val layout = layoutInflater.inflate(R.layout.item_alertdialog, null)
            val build = AlertDialog.Builder(it.context).apply {
                setView(layout)
            }
            val dialog = build.create()
            dialog.show()

            layout.number_picker.minValue = 1
            layout.number_picker.maxValue = ad_count
            if (selectNum != 0) layout.number_picker.value = selectNum

            layout.back.setOnClickListener{
                dialog.dismiss()
            }
            //구매하기
            layout.btn_no.setOnClickListener {
                selectNum = layout.number_picker.value

                val intent = Intent(this, BuyActivity::class.java)
                intent.putExtra("foodCount", selectNum)
                intent.putExtra("data", datas)
                startActivity(intent)
            }
            //장바구니
            layout.btn_ok.setOnClickListener {
                //=====================================================//
                selectNum = layout.number_picker.value

                sendCartData(selectNum)

                //=====================================================//
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

        // 가게 정보가 담긴 부분을 눌렀을 때, 가게 상세 정보(FoodStoreDetailActivity) 창으로 넘어감
        store_box.setOnClickListener {
            val intent = Intent(this, FoodStoreDetailActivity::class.java)
            intent.putExtra("data", datas)
            startActivityForResult(intent, NEW_STORE)
        }

        review_img.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

        review.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }
    }

    fun start(){
        started = true
        thread(start=true){
            while (true){
                Thread.sleep(1000)
                total = total?.minus(1)
                total?.let { handler.sendEmptyMessage(it) }
            }
        }
    }

    fun formatTime(time:Int) : String{
        var min = time?.div(60)
        val hour = String.format("%02d",min?.div(60))
        val sec = String.format("%02d",time?.rem(60))
        var nmin: String? = null
        if (min != null) {
            nmin = String.format("%02d",min%60)
        }
        return "$hour : $nmin : $sec"
    }

    //장바구니 추가
    fun sendCartData(selectedNum : Int){
        val layout = layoutInflater.inflate(R.layout.item_alertdialog, null)
        Log.d("Cart insert", "Cart start...")
        var result = ""
        try{
            val userId = UserSingleton.getInstance().userId.toString()
            val pdName = detail_food_name.text!!.toString()
            val storeName = store_de_name.text!!.toString()
            val pdCount = selectedNum

            Log.d("앱에서 보낸값", "$userId, $pdName, $storeName, $pdCount")

            var task = CartTask()
            val result = task.execute(userId, pdName, storeName, pdCount.toString()).get();
            Log.d("받은값", result)
            Log.d("Cart insert", "Cart end...")
        }catch (e :Exception){
            e.printStackTrace()
        }
        if(result == "true"){

        }else{
            Log.d("Cart insert", "Cart fail...")
        }
    }
}

