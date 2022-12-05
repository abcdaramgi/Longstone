package com.example.applicationtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_st_user.*

class StUserFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_st_user)

        btn_st_logout.setOnClickListener({
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        })
        cardview_stinfo.setOnClickListener({
            val intent = Intent(this, StInfoScreen::class.java)
            startActivity(intent)
        })
        cardview_orderinfo.setOnClickListener({
            val intent = Intent(this, OrderHistoryScreen::class.java)
            startActivity(intent)
        })
        cardview_streview.setOnClickListener({
            val intent = Intent(this, StReviewScreen::class.java)
            startActivity(intent)
        })
    }
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
}