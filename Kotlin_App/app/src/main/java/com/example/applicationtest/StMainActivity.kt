package com.example.applicationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_st_main.*

class StMainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private lateinit var staddfragment: StAddFragment
    private lateinit var sthomefragment: StHomeFragment
    private lateinit var stuserfragment: StUserFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st_main)

        st_nav_view.setOnNavigationItemSelectedListener(this)
        setSupportActionBar(toolbar) //커스텀한 toolbar를 사용
        //액션바에 표시되는 제목의 표시유무 설정. false로 해야 custom한 툴바의 이름이 화면에 보임
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.title = "FOODTIME"

        sthomefragment = StHomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragment_frame, sthomefragment).commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_plus -> {
                staddfragment = StAddFragment.newInstance()
                supportFragmentManager.beginTransaction().add(R.id.fragment_frame, staddfragment).commit()
            }
            R.id.menu_Home -> {
                sthomefragment = StHomeFragment.newInstance()
                supportFragmentManager.beginTransaction().add(R.id.fragment_frame, sthomefragment).commit()
            }
            R.id.menu_Person -> {
                stuserfragment = StUserFragment.newInstance()
                supportFragmentManager.beginTransaction().add(R.id.fragment_frame, stuserfragment).commit()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.st_toolbar_menu, menu)
        return true
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_notifications -> {  //장바구니

            }
        }
    }*/

}