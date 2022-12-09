package com.example.applicationtest

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.applicationtest.DTO.FoodData
import com.example.applicationtest.DTO.StoreFoodDTO
import com.example.applicationtest.Singleton.SellerSingleton
import com.example.applicationtest.Singleton.UserSingleton
import com.example.applicationtest.Transport.SaveTokenTask
import com.example.applicationtest.Transport.StoreFoodListTask
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_st_main.*
import kotlinx.android.synthetic.main.fragment_st_user.*
import org.json.JSONArray
import org.json.JSONObject


class StMainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private lateinit var staddfragment: StAddFragment
    private lateinit var sthomefragment: StHomeFragment
    private lateinit var stuserfragment: StUserFragment

    var stfoodList: ArrayList<FoodData> = arrayListOf(
//        FoodData("소금빵","용산동",2500,2300,R.drawable.image_bread1.toString(),5,"가게1",R.drawable.ic_baseline_home_24,2),
//        FoodData("오징어젓갈","신당동",12000,8000,R.drawable.image_bread1.toString(),6,"가게2",R.drawable.ic_baseline_home_24,5),
//        FoodData("샐러드","신당동",25200, 17000,R.drawable.image_bread1.toString(),8,"가게3",R.drawable.ic_baseline_home_24,3),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st_main)

        st_nav_view.setOnNavigationItemSelectedListener(this)
        setSupportActionBar(toolbar) //커스텀한 toolbar를 사용
        //액션바에 표시되는 제목의 표시유무 설정. false로 해야 custom한 툴바의 이름이 화면에 보임
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val transaction = supportFragmentManager.beginTransaction()
        getStoreFoodData()
        updateToken()
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
    fun getStoreFoodData(){
        stfoodList!!.clear();
        var result = ""
        try{
            Log.d("StoreFood List", "StoreFood List start...")

            val sellerId = SellerSingleton.getInstance().sellerId.toString();
            Log.d("앱에서 보낸값", "$sellerId")

            val task = StoreFoodListTask()
            result = task.execute("$sellerId").get()
            Log.d("받은값", result)

            if(result != null){
                val `object` = JSONObject(result)
                val array = `object`.get("post") as JSONArray

                for(i: Int in 0..array.length()-1){
                    var row = array.getJSONObject(i)
                    var storeFoodDTO = StoreFoodDTO()


                    storeFoodDTO.setSellerId(row.getString("sellerId"))
                    storeFoodDTO.setPdName(row.getString("pdName"))
                    storeFoodDTO.setStoreAddr(row.getString("storeAddr"))
                    storeFoodDTO.setPdPrice(row.getInt("pdPrice"))
                    storeFoodDTO.setSaleprice(row.getInt("saleprice"))
                    storeFoodDTO.setStatus(row.getString("status"))
                    storeFoodDTO.setImgUrl(row.getString("imgUrl"))

                    stfoodList!!.add(FoodData(
                        storeFoodDTO.getPdName(),
                        storeFoodDTO.getStoreAddr(),
                        storeFoodDTO.getPdPrice(),
                        storeFoodDTO.getSaleprice(),
                        storeFoodDTO.getImgUrl(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        storeFoodDTO.getStatus(),
                        null,
                        null,
                        storeFoodDTO.getSellerId()
                    ));

                }
            }
            else{
                Log.d("storeFood", "storeFood fail...")
            }
            Log.d("storeFood List", "OstoreFoodList end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
    }

    private fun updateToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result

            val testid = MyApplication.prefs.getString("id", "0")
            Log.i("plz", testid)
            Log.i("plz", SellerSingleton.getInstance().sellerId)
            if(testid == SellerSingleton.getInstance().sellerId){
                val task = SaveTokenTask()
                val result = task.execute( SellerSingleton.getInstance().sellerId, token, "user").get()
                if(result == "true"){
                    Log.i("saveToken", "성공적으로 소비자 토큰을 저장함")
                }
            }else{
                Log.i("saveToken", "토큰저장 실패")
            }
        })

    }
}