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
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.fragment_bell_screen.*
import kotlinx.android.synthetic.main.fragment_bell_screen.toolbar
import kotlinx.android.synthetic.main.fragment_cart_screen.*
import kotlinx.android.synthetic.main.fragment_prefer_screen.*
import kotlinx.android.synthetic.main.item_alertdialog.view.*
import kotlinx.android.synthetic.main.item_menu_view.view.*
import kotlinx.android.synthetic.main.item_today_food_detail.*

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
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecycler() {
        cartAdapter = CartAdapter(this)
        re_cart.adapter = cartAdapter

        datas.apply {
            add(ItemCart(false,"소영이네 빵집","생크림 소금빵",R.drawable.image_bread1, 2000,3))
            add(ItemCart(false,"갱초이 정육점","양념불고기(간장)",R.drawable.image_bread1, 5500,2))
            add(ItemCart(false,"혜르무르 카페","김혜르무르트 2세 쿠키",R.drawable.image_bread1,1200,1))
        }
        cartAdapter.datas = datas
        cartAdapter.notifyDataSetChanged()
        textView22.text = "상품 " + cartAdapter.getItemCount().toString()+ "개"
    }
}
