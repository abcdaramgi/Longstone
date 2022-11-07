package com.example.applicationtest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_map_screen.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private lateinit var homeScreen: HomeScreen
    private lateinit var searchScreen: SearchScreen
    private lateinit var mapScreen: MapScreen
    private lateinit var preferScreen:PreferScreen
    private lateinit var userScreen: UserScreen
    private lateinit var bellScreen: BellScreen
    private lateinit var cartScreen: CartScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener(this)

        setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.

        homeScreen = HomeScreen.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fl_container, homeScreen).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(Appitem: MenuItem): Boolean {
        when(Appitem?.itemId){
            R.id.action_bell -> {
                bellScreen = BellScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, bellScreen).commit()
            }
            R.id.action_cart -> {
                cartScreen = CartScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, cartScreen).commit()
            }
            else -> return super.onOptionsItemSelected(Appitem)
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.first -> {
                homeScreen = HomeScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, homeScreen).commit()
            }
            R.id.second -> {
                searchScreen = SearchScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, searchScreen).commit()
            }
            R.id.third -> {
                mapScreen = MapScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, mapScreen).commit()
            }
            R.id.four -> {
                preferScreen = PreferScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, preferScreen).commit()
            }
            R.id.five -> {
                userScreen = UserScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, userScreen).commit()
            }
        }
        return true
    }
}
