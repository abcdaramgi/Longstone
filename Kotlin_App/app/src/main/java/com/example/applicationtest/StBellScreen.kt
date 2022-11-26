package com.example.applicationtest

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_bell_screen.rv_bell
import kotlinx.android.synthetic.main.fragment_st_bell_screen.*
import java.time.LocalDateTime

class StBellScreen : AppCompatActivity() {
    lateinit var stbellAdapter: StBellAdapter
    val datas = mutableListOf<OrderBell>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_st_bell_screen)

        setSupportActionBar(st_toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시

        initRecycler()
        st_rv_bell.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
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
        stbellAdapter = StBellAdapter(this)
        st_rv_bell.adapter = stbellAdapter

        //알림 정보 넣는거
        datas.apply {
            add(OrderBell(FoodName = "음식이름", Customer = "손님이름", OrderTime = LocalDateTime.of(2022,11,16,19,30), FoodCost = "금액원"))

            stbellAdapter.datas = datas
            stbellAdapter.notifyDataSetChanged()

        }
    }
}