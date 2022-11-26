package com.example.applicationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_st_main.*
import kotlinx.android.synthetic.main.fragment_st_home.*
import kotlinx.android.synthetic.main.fragment_st_home.view.*
import kotlinx.android.synthetic.main.fragment_st_user.*


class StMainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private lateinit var staddfragment: StAddFragment
    private lateinit var sthomefragment: StHomeFragment
    private lateinit var stuserfragment: StUserFragment

    var stfoodList: ArrayList<FoodData> = arrayListOf(
        FoodData("소금빵","용산동",2500,2300,R.drawable.image_bread1,5,"가게1",R.drawable.ic_baseline_home_24,2),
        FoodData("오징어젓갈","신당동",12000,8000,R.drawable.image_bread1,6,"가게2",R.drawable.ic_baseline_home_24,5),
        FoodData("샐러드","신당동",25200, 17000,R.drawable.image_bread1,8,"가게3",R.drawable.ic_baseline_home_24,3),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st_main)

        st_nav_view.setOnNavigationItemSelectedListener(this)
        setSupportActionBar(toolbar) //커스텀한 toolbar를 사용
        //액션바에 표시되는 제목의 표시유무 설정. false로 해야 custom한 툴바의 이름이 화면에 보임
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val transaction = supportFragmentManager.beginTransaction()
        R.id.menu_Home
        transaction.replace(
            R.id.fragment_frame,
            StHomeFragment()
        )
        transaction.commit()
        intent.putExtra("DataList",stfoodList)
        /*sthomefragment = StHomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragment_frame, sthomefragment).commit()
        */
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var transaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.menu_plus -> {
                val intent = Intent(this, StAddFragment::class.java)
                startActivity(intent)
            }
            R.id.menu_Home -> {
                transaction.replace(
                    R.id.fragment_frame,
                    StHomeFragment()
                )
                transaction.commit()
                intent.putExtra("DataList",stfoodList)
               /* sthomefragment = StHomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, sthomefragment).commit()*/
            }
            R.id.menu_Person -> {
                val intent = Intent(this, StUserFragment::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.st_toolbar_menu, menu)
        return true
    }
    //액션버튼 클릭
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_notifications -> {
                val intent = Intent(this, StBellScreen::class.java)
                startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}