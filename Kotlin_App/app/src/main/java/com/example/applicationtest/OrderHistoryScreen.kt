package com.example.applicationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicationtest.DTO.FoodData
import com.example.applicationtest.DTO.OrderHistoryDTO
import com.example.applicationtest.Singleton.SellerSingleton
import com.example.applicationtest.Transport.OrderHistoryTask
import com.example.applicationtest.Transport.StoreFoodListTask
import kotlinx.android.synthetic.main.activity_order_history_screen.*
import kotlinx.android.synthetic.main.fragment_st_bell_screen.*
import kotlinx.android.synthetic.main.fragment_st_bell_screen.st_rv_bell
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime

class OrderHistoryScreen : AppCompatActivity() {
    lateinit var orderAdapter: OrderAdapter
    val datas = mutableListOf<ItemOrder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history_screen)

        setSupportActionBar(st_toolbar_order) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시

        initRecycler()
        st_rv_orderhistory.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
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
        orderAdapter = OrderAdapter(this)
        st_rv_orderhistory.adapter = orderAdapter


        var result = ""
        try{
            Log.d("OrderHistory List", "OrdoerHistory List start...")

            val sellerId = SellerSingleton.getInstance().sellerId.toString();
            Log.d("앱에서 보낸값", "$sellerId")

            val task = OrderHistoryTask()
            result = task.execute("$sellerId").get()
            Log.d("받은값", result)

            if(result != null){
                val `object` = JSONObject(result)
                val array = `object`.get("orderHistory") as JSONArray

                for(i: Int in 0..array.length()-1){
                    var row = array.getJSONObject(i)
                    var orderHistoryDTO = OrderHistoryDTO()

                    orderHistoryDTO.setUserId(row.getString("userId"))
                    orderHistoryDTO.setPdName(row.getString("pdName"))
                    orderHistoryDTO.setSalePrice(row.getInt("salePrice"))
                    orderHistoryDTO.setOrderCount(row.getString("orderCount"))
                    orderHistoryDTO.setUpdateAt(row.getString("updateAt"))

                    datas.apply {
//                        add(ItemOrder("주문한 사람","음식 이름","갯수",
//                            "2022-11-24","10000"),
//                        )
                        add(ItemOrder(orderHistoryDTO.getUserId(), orderHistoryDTO.getPdName(),
                        orderHistoryDTO.getOrderCount(), orderHistoryDTO.getUpdateAt(),
                        orderHistoryDTO.getSalePrice().toString()))
                        orderAdapter.datas = datas
                        orderAdapter.notifyDataSetChanged()
                    }



                }
            }
            else{
                Log.d("storeFood", "storeFood fail...")
            }
            Log.d("storeFood List", "OstoreFoodList end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
    }

}