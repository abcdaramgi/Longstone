package com.example.applicationtest
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private lateinit var homeScreen: HomeScreen
    private lateinit var searchScreen: SearchScreen
    private lateinit var mapScreen: MapScreen
    private lateinit var preferScreen:PreferScreen
    private lateinit var userScreen: UserScreen

    var dataList: ArrayList<FoodData> = arrayListOf(
        FoodData("뚜레주르","구암동","2500원 -> 1200원",R.drawable.image_bread1),
        FoodData("뚜레주르","구암동","2500원 -> 1200원",R.drawable.image_bread1),
        FoodData("뚜레주르","구암동","2500원 -> 1200원", R.drawable.image_bread1),
        //TodayData("첫 번째 데이터2", "두 번째 데이터2", "세 번째 데이터2")
        /*HomeData("첫 번째 데이터3", "두 번째 데이터3", "세 번째 데이터3"),
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
        HomeData("첫 번째 데이터15", "두 번째 데이터15", "세 번째 데이터15")*/
    )

    var storeList: ArrayList<StoreData> = arrayListOf(
        StoreData("뚜레주르","빵, 음료, 샐러드",R.drawable.image_bread1),
        StoreData("유가네 닭갈비","닭갈비, 볶음밥, 막국수, ",R.drawable.image_bread1),
        StoreData("봉대박찜닭","찜닭, 샐러드",R.drawable.image_bread1),
        //TodayData("첫 번째 데이터2", "두 번째 데이터2", "세 번째 데이터2")
        /*HomeData("첫 번째 데이터3", "두 번째 데이터3", "세 번째 데이터3"),
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
        HomeData("첫 번째 데이터15", "두 번째 데이터15", "세 번째 데이터15")*/
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener(this)

        setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.fl_container,
            HomeScreen(),
        )
        transaction.commit()
        intent.putExtra("DataList",dataList)

        /*homeScreen = HomeScreen.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fl_container, homeScreen).commit()*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //액션버튼 클릭 했을 때
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var transaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.first -> {
                transaction.replace(
                    R.id.fl_container,
                    HomeScreen()
                )
                transaction.commit()
                intent.putExtra("DataList",dataList)

                /*homeScreen = HomeScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, homeScreen).commit()*/
            }
            R.id.second -> {
                    transaction.replace(
                        R.id.fl_container,
                        SearchScreen()
                    )
                    transaction.commit()
                /*searchScreen = SearchScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, searchScreen).commit()*/
            }
            R.id.third -> {
                mapScreen = MapScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, mapScreen).commit()
            }
            R.id.four -> {
                transaction.replace(
                    R.id.fl_container,
                    PreferScreen()
                )
                transaction.commit()
                intent.putExtra("DataList",storeList)
                /*preferScreen = PreferScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, preferScreen).commit()*/
            }
            R.id.five -> {
                userScreen = UserScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, userScreen).commit()
            }
        }
        return true
    }

    private fun getHashKey() {
        var packageInfo: PackageInfo? = null
        try{
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        if (packageInfo == null) {
            Log.d("hashKey", "null")
        }
        packageInfo?.signatures?.forEach {
            try {
                val md = MessageDigest.getInstance("SHA")
                md.update(it.toByteArray())
                Log.d("hashKey", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
                Log.e("KeyHash", "Unable to get MessageDigest. signature=$it", e)
            }
        }
    }
}
