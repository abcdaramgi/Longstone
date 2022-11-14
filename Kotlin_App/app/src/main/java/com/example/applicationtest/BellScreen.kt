package com.example.applicationtest

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bell_screen.*
import kotlinx.android.synthetic.main.fragment_bell_screen.toolbar

class BellScreen : AppCompatActivity() {
    lateinit var bellAdapter: BellAdapter
    val datas = mutableListOf<ItemBell>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_bell_screen)

        setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시

        initRecycler()
        rv_bell.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
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
        bellAdapter = BellAdapter(this)
        rv_bell.adapter = bellAdapter


        datas.apply {
            add(ItemBell(storeName = "가게1", storeInfo = "20분 후 가게 마감"))
            add(ItemBell(storeName = "가게2", storeInfo = "20분 후 가게 마감"))
            add(ItemBell(storeName = "가게3", storeInfo = "20분 후 가게 마감"))
            add(ItemBell(storeName = "가게4", storeInfo = "20분 후 가게 마감"))
            add(ItemBell(storeName = "가게5", storeInfo = "20분 후 가게 마감"))
            add(ItemBell(storeName = "가게6", storeInfo = "20분 후 가게 마감"))
            add(ItemBell(storeName = "가게7", storeInfo = "20분 후 가게 마감"))
            add(ItemBell(storeName = "가게8", storeInfo = "20분 후 가게 마감"))
            add(ItemBell(storeName = "가게9", storeInfo = "20분 후 가게 마감"))
            add(ItemBell(storeName = "가게10", storeInfo = "20분 후 가게 마감"))
            add(ItemBell(storeName = "가게11", storeInfo = "20분 후 가게 마감"))

            bellAdapter.datas = datas
            bellAdapter.notifyDataSetChanged()
        }
    }
}