package com.example.applicationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationtest.DTO.OrderListDTO
import com.example.applicationtest.Singleton.UserSingleton
import com.example.applicationtest.Transport.OrderListTask
import kotlinx.android.synthetic.main.activity_buy_list.*
import org.json.JSONArray
import org.json.JSONObject

class BuyListActivity : AppCompatActivity() {
    // BuyListAdapter CartAdapter 안에 작성되어 있음
    lateinit var buyListAdapter: BuyListAdapter
    val data = mutableListOf<BuyListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_list)

        setSupportActionBar(buy_toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시

        initRecycler()
    }

    //액션바의 뒤로가기 버튼 클릭 이벤트
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

    private fun initRecycler() {
        buyListAdapter = BuyListAdapter(this)
        buy_list.adapter = buyListAdapter

        //=========================================================//
        data!!.clear();
        var result = ""
        try{
            Log.d("Order List", "Order List Start...")
            val userId = UserSingleton.getInstance().userId.toString()
            Log.d("앱에서 보낸값", "$userId")

            val task = OrderListTask()
            result = task.execute(userId).get()
            Log.d("받은값", result)

            if(result != null){
                val `object` = JSONObject(result)
                val array = `object`.get("orderList") as JSONArray

                for(i: Int in 0 .. array.length()-1){
                    var row = array.getJSONObject(i)
                    var orderListDTO = OrderListDTO()

                    orderListDTO.setpdName(row.getString("pdName"))
                    orderListDTO.setstoreName(row.getString("storeName"))
                    orderListDTO.setorderCount(row.getString("orderCount"))
                    orderListDTO.setimgUrl(row.getString("imgUrl"))
                    orderListDTO.setupdateAt(row.getString("updateAt"))

                    Log.d("pdName : ", orderListDTO.getpdName())
                    Log.d("storeName : ", orderListDTO.getstoreName())
                    Log.d("orderCount : ", orderListDTO.getorderCount())
                    Log.d("imgUrl : ", orderListDTO.getimgUrl())
                    Log.d("date : ", orderListDTO.getupdateAt())

                    data!!.add(BuyListItem(orderListDTO.getpdName(), orderListDTO.getstoreName(),
                                        orderListDTO.getorderCount(), orderListDTO.getimgUrl(),orderListDTO.getupdateAt()))
                }
            }
            else{
                Log.d("Order List", "Order List Fail...")
            }
            Log.d("Order List", "Order List end...")
        }catch (e : Exception){
            e.printStackTrace()
        }
        //=========================================================//
//        data.apply {
//            add(BuyListItem("돈가스","가게1","5",R.drawable.image_food1.toString()))
//            add(BuyListItem("소금빵","가게1","3",R.drawable.image_bread1.toString()))
//            add(BuyListItem("오징어볶음","가게1","5",R.drawable.image_food1.toString()))
//        }
        buyListAdapter.data = data
        buyListAdapter.notifyDataSetChanged()
    }
}
