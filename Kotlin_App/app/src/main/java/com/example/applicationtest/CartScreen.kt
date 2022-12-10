package com.example.applicationtest

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationtest.DTO.CartListDTO
import com.example.applicationtest.DTO.OrderListDTO
import com.example.applicationtest.Singleton.UserSingleton
import com.example.applicationtest.Transport.CartListTask
import com.example.applicationtest.Transport.OrderListTask
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.fragment_bell_screen.*
import kotlinx.android.synthetic.main.fragment_bell_screen.toolbar
import kotlinx.android.synthetic.main.fragment_cart_screen.*
import kotlinx.android.synthetic.main.fragment_prefer_screen.*
import kotlinx.android.synthetic.main.item_alertdialog.view.*
import kotlinx.android.synthetic.main.item_menu_view.view.*
import kotlinx.android.synthetic.main.item_today_food_detail.*
import org.json.JSONArray
import org.json.JSONObject

// 장바구니 화면, 상단바에 있는 장바구니 버튼을 눌렀을 때 나타나는 화면
// 상품의 디테일 화면에서 장바구니 버튼을 눌렀을 때 이 곳에 물건이 추가되도록 해야함
class CartScreen : AppCompatActivity() {
    lateinit var cartAdapter: CartAdapter
    val datas = mutableListOf<ItemCart>()
    var buy = ItemCart()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_cart_screen)

        setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시

        initRecycler()
        re_cart.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        buy_bt.setOnClickListener{
            var item : String = "Selected item : \n"
            var ck : Int = 0

            for(i :Int in 0..datas.size-1)
                if(datas[i].isChecked == true) {
                    ck = ck + 1
                }
            if(ck == 1)
            {
                for(i :Int in 0..datas.size-1)
                    if(datas[i].isChecked == true) {
                        buy = datas[i]
                    }
                val intent = Intent(this, CartBuyActivity::class.java)
                intent.putExtra("buy",buy)
                startActivity(intent)
            }
            else{
                val builder = AlertDialog.Builder(this)
                    .setMessage("상품은 1개씩 구매할 수 있습니다.")
                    .setPositiveButton("확인",
                        DialogInterface.OnClickListener { dialog, id ->
                        })
                builder.show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecycler() {
        cartAdapter = CartAdapter(this)
        re_cart.adapter = cartAdapter
        //===================================================//
        datas!!.clear()
        var result = ""
        try{
            Log.d("Cart List", "Cart List Start...")
            val userId = UserSingleton.getInstance().userId.toString()
            Log.d("앱에서 보낸값", "$userId")

            val task = CartListTask()
            result = task.execute(userId).get()
            Log.d("받은값", result)

            if(result != null) {
                val `object` = JSONObject(result)
                val array = `object`.get("cartList") as JSONArray

                for(i: Int in 0 .. array.length()-1) {
                    var row = array.getJSONObject(i)
                    var cartListDTO = CartListDTO()

                    cartListDTO.setstoreName(row.getString("storeName"))
                    cartListDTO.setpdName(row.getString("pdName"))
                    cartListDTO.setimgUrl(row.getString("imgUrl"))
                    cartListDTO.setpdPrice(row.getInt("pdPrice"))
                    cartListDTO.setpdCount(row.getInt("pdCount"))
                    cartListDTO.setpdSale(row.getDouble("pdSale"))
                    cartListDTO.setphoneNum(row.getString("phoneNum"))
                    cartListDTO.setstoreName(row.getString("storeAddr"))

                    Log.d("storeName : ", cartListDTO.getstoreName())
                    Log.d("pdName : ", cartListDTO.getpdName())
                    Log.d("imgUrl : ", cartListDTO.getimgUrl())
                    Log.d("pdPrice : ", cartListDTO.getpdPrice().toString())
                    Log.d("pdCount : ", cartListDTO.getpdCount().toString())
                    Log.d("pdSale : ", cartListDTO.getpdSale().toString())
                    Log.d("phoneNum : ", cartListDTO.getphoneNum().toString())
                    Log.d("storeAddr : ", cartListDTO.getstoreName().toString())

                    datas!!.add(ItemCart(false, cartListDTO.getstoreName(), cartListDTO.getpdName(),
                                cartListDTO.getimgUrl(), cartListDTO.getpdPrice(), cartListDTO.getpdCount(), cartListDTO.getpdSale(),
                                cartListDTO.getphoneNum(), cartListDTO.getstoreAddr()))
                }
            }
            else{
                Log.d("Cart List", "Cart List fail...")
            }
            Log.d("Cart List", "Cart List end...")
        }catch (e :Exception){
            e.printStackTrace()
        }
        //===================================================//
//        datas.apply {
//            add(ItemCart(false,"소영이네 빵집","생크림 소금빵",R.drawable.image_bread1.toString(), 2000,3))
//            add(ItemCart(false,"갱초이 정육점","양념불고기(간장)",R.drawable.image_bread1.toString(), 5500,2))
//            add(ItemCart(false,"혜르무르 카페","김혜르무르트 2세 쿠키",R.drawable.image_bread1.toString(),1200,1))
//        }
        cartAdapter.datas = datas
        cartAdapter.notifyDataSetChanged()
        textView22.text = "상품 " + cartAdapter.getItemCount().toString()+ "개"
    }
}
