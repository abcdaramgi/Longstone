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
        FoodData("뚜레주르","구암동","2500원 -> 1200원",R.drawable.image_bread1),
        FoodData("뚜레주르","구암동","2500원 -> 1200원",R.drawable.image_bread1),
        FoodData("뚜레주르","구암동","2500원 -> 1200원", R.drawable.image_bread1),
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
        intent.putExtra("DataList",stfoodList)
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

    override fun onOptionsItemSelected(Appitem: MenuItem): Boolean {
        when(Appitem?.itemId){
            R.id.action_bell -> {
                val intent = Intent(this, BellScreen::class.java)
                startActivity(intent)
            }
            R.id.action_cart -> {
                val intent = Intent(this, CartScreen::class.java)
                startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(Appitem)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.st_toolbar_menu, menu)
        return true
    }

}