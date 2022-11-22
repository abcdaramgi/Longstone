package com.example.applicationtest

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.fragment_bell_screen.*
import kotlinx.android.synthetic.main.fragment_bell_screen.toolbar
import kotlinx.android.synthetic.main.fragment_cart_screen.*

// 장바구니 화면, 상단바에 있는 장바구니 버튼을 눌렀을 때 나타나는 화면
// 상품의 디테일 화면에서 장바구니 버튼을 눌렀을 때 이 곳에 물건이 추가되도록 해야함
class CartScreen : AppCompatActivity() {
    lateinit var cartAdapter: CartAdapter
    val datas = mutableListOf<ItemCart>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_cart_screen)

        setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시

        initRecycler()
        re_cart.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
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
            add(ItemCart("5점","바삭하고 맛있어요.",R.drawable.image_bread1))
            add(ItemCart("5점","바삭하고 맛있어요.",R.drawable.image_bread1))
            add(ItemCart("5점","바삭하고 맛있어요.",R.drawable.image_bread1))
        }
        cartAdapter.datas = datas
        cartAdapter.notifyDataSetChanged()
        textView22.text = "상품" + cartAdapter.getItemCount().toString()+ "개"
    }
}
