package com.example.applicationtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
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

        })
    }
}