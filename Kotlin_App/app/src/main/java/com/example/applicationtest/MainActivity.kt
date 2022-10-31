package com.example.applicationtest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private lateinit var homeScreen: HomeScreen
    private lateinit var searchScreen: SearchScreen
    private lateinit var mapScreen: MapScreen
    private lateinit var preferScreen:PreferScreen
    private lateinit var userScreen: UserScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener(this)

        homeScreen = HomeScreen.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fl_container, homeScreen).commit()
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
