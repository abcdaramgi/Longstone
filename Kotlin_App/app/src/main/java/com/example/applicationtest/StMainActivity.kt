package com.example.applicationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_st_main.*
import kotlinx.android.synthetic.main.fragment_st_home.*
import kotlinx.android.synthetic.main.fragment_st_home.view.*


class StMainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private lateinit var staddfragment: StAddFragment
    private lateinit var sthomefragment: StHomeFragment
    private lateinit var stuserfragment: StUserFragment

    var dataList: ArrayList<HomeData> = arrayListOf(
        HomeData("첫 번째 데이터1", "두 번째 데이터1", "세 번째 데이터1"),
        HomeData("첫 번째 데이터2", "두 번째 데이터2", "세 번째 데이터2"),
        HomeData("첫 번째 데이터3", "두 번째 데이터3", "세 번째 데이터3"),
        HomeData("첫 번째 데이터4", "두 번째 데이터4", "세 번째 데이터4"),
        HomeData("첫 번째 데이터5", "두 번째 데이터5", "세 번째 데이터5"),
        HomeData("첫 번째 데이터6", "두 번째 데이터6", "세 번째 데이터6"),
        HomeData("첫 번째 데이터7", "두 번째 데이터7", "세 번째 데이터7"),
        HomeData("첫 번째 데이터8", "두 번째 데이터8", "세 번째 데이터8"),
        HomeData("첫 번째 데이터9", "두 번째 데이터9", "세 번째 데이터9"),
        HomeData("첫 번째 데이터10", "두 번째 데이터10", "세 번째 데이터10"),
        HomeData("첫 번째 데이터11", "두 번째 데이터11", "세 번째 데이터11"),
        HomeData("첫 번째 데이터12", "두 번째 데이터12", "세 번째 데이터12"),
        HomeData("첫 번째 데이터13", "두 번째 데이터13", "세 번째 데이터13"),
        HomeData("첫 번째 데이터14", "두 번째 데이터14", "세 번째 데이터14"),
        HomeData("첫 번째 데이터15", "두 번째 데이터15", "세 번째 데이터15")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st_main)

        st_nav_view.setOnNavigationItemSelectedListener(this)
        setSupportActionBar(toolbar) //커스텀한 toolbar를 사용
        //액션바에 표시되는 제목의 표시유무 설정. false로 해야 custom한 툴바의 이름이 화면에 보임
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.fragment_frame,
            StHomeFragment()
        )
        transaction.commit()
        intent.putExtra("DataList",dataList)
        /*sthomefragment = StHomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragment_frame, sthomefragment).commit()
        */
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var transaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.menu_plus -> {
                staddfragment = StAddFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, staddfragment).commit()
            }
            R.id.menu_Home -> {
                transaction.replace(
                    R.id.fragment_frame,
                    StHomeFragment()
                )
                transaction.commit()
                intent.putExtra("DataList",dataList)
               /* sthomefragment = StHomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, sthomefragment).commit()*/
            }
            R.id.menu_Person -> {
                stuserfragment = StUserFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, stuserfragment).commit()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.st_toolbar_menu, menu)
        return true
    }

}