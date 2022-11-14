package com.example.applicationtest

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_bell_screen.*

class CartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_cart_screen)

        setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
}