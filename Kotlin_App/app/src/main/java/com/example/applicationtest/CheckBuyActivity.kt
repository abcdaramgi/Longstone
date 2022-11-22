package com.example.applicationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_bell_screen.*

class CheckBuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_buy)

        setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시
    }
}