package com.example.applicationtest
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationtest.DTO.FoodData
import com.example.applicationtest.DTO.OnSalePostDTO
import com.example.applicationtest.DTO.StoreDTO
import com.example.applicationtest.FireBase.MyFirebaseMessagingService
import com.example.applicationtest.Singleton.SellerSingleton
import com.example.applicationtest.Singleton.UserSingleton
import com.example.applicationtest.Transport.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
//import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    private lateinit var homeScreen: HomeScreen
    private lateinit var searchScreen: SearchScreen
    private lateinit var mapScreen: MapScreen
    private lateinit var preferScreen:PreferScreen
    private lateinit var userScreen: UserScreen

    var dataList: MutableList<FoodData>? = mutableListOf(
//        FoodData("소금빵","용산동",2500,2300,"",5,"가게1",R.drawable.ic_baseline_home_24,2,1,"11"),
//        FoodData("오징어젓갈","신당동",10000,5500,"",6,"가게2",R.drawable.ic_baseline_home_24,5,1,"11"),
//        FoodData("샐러드","신당동",12000, 8000,R.drawable.image_food1,8,"가게3",R.drawable.ic_baseline_home_24,3),
//        FoodData("매추리알","신당동",5000, 3000,R.drawable.image_food3,8,"가게3",R.drawable.ic_baseline_home_24,3)
    )
    var storeList: MutableList<StoreData>? = mutableListOf();
//    var storeList: MutableList<StoreData> = mutableListOf(
//        StoreData("뚜레주르","빵, 음료, 샐러드",R.drawable.image_bread1),
//        StoreData("유가네 닭갈비","닭갈비, 볶음밥, 막국수, ",R.drawable.image_bread1),
//        StoreData("봉대박찜닭","찜닭, 샐러드",R.drawable.image_bread1),
//    )

    var storeList1: MutableList<StoreData>? = mutableListOf();

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //test
        MyFirebaseMessagingService().getFirebaseToken()
        initDynamicLink()
        getNotificationList()
        updateToken()


        nav_view.setOnNavigationItemSelectedListener(this)
            getHashKey()
        setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.

        val transaction = supportFragmentManager.beginTransaction()
        getTodayData()
        transaction.replace(
            R.id.fl_container,
            HomeScreen(),
        )
        transaction.commit()
        intent.putExtra("DataList",dataList as ArrayList)
        //intent.putExtra("DataList",dataList)

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
                getTodayData()
                transaction.replace(
                    R.id.fl_container,
                    HomeScreen()
                )
                transaction.commit()
                intent.putExtra("DataList",dataList as ArrayList)
                /*homeScreen = HomeScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, homeScreen).commit()*/
            }
            R.id.second -> {
                getStoreData()
                transaction.replace(
                    R.id.fl_container,
                    SearchScreen()
                )
                transaction.commit()
                intent.putExtra("DataList",storeList1 as ArrayList)
                /*searchScreen = SearchScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, searchScreen).commit()*/
            }
            R.id.third -> {
                mapScreen = MapScreen.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fl_container, mapScreen).commit()
            }
            //즐겨찾기
            R.id.four -> {
                getFavoritesData()
                transaction.replace(
                    R.id.fl_container,
                    PreferScreen()
                )
                transaction.commit()
                intent.putExtra("DataList",storeList as ArrayList)
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

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)

    }

//    private fun initFirebase() {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                Log.d("알림알림!", task.result)
//            }
//        }
//    }

    private fun initDynamicLink() {
        val dynamicLinkData = intent.extras
        if (dynamicLinkData != null) {
            var dataStr = "DynamicLink 수신받은 값\n"
            for (key in dynamicLinkData.keySet()) {
                dataStr += "key: $key / value: ${dynamicLinkData.getString(key)}\n"
            }
            Log.d("알림알림@", dataStr)
        }
    }

    fun getNotificationList(){
        try{
            Log.d("Favorites List", "Favorites List start...")

            val userId = UserSingleton.getInstance().userId.toString();
            Log.d("앱에서 보낸값", "$userId")

            val task = FavoritesListTask()
            val result = task.execute(userId).get()
            Log.d("받은값", result)

            if(result != null){
                val `object` = JSONObject(result)
                val array = `object`.get("store") as JSONArray

                for(i: Int in 0..array.length()-1){
                    var row = array.getJSONObject(i)
                    var storeDTO = StoreDTO()

                    storeDTO.setName(row.getString("sellerId"))
                    val sellerId = storeDTO.getName()

                    FirebaseMessaging.getInstance().subscribeToTopic(sellerId.toString()).addOnCompleteListener { task ->
                        var msg = "Subscribed"
                        if (!task.isSuccessful) {
                            msg = "Subscribe failed"
                        }
                        Log.d(MyFirebaseMessagingService.TAG, msg!!)
                        Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
                    }
                }
            }
            else{
                Log.d("Favorites", "Favorites fail...")
            }

            Log.d("Favorites List", "Favorites List end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
    }


    private fun updateToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result

            val testid = MyApplication.prefs.getString("id", "0")
            Log.i("plz", testid)
            Log.i("plz", UserSingleton.getInstance().userId)
            if(testid == UserSingleton.getInstance().userId){
                val task = SaveTokenTask()
                val result = task.execute( UserSingleton.getInstance().userId, token, "user").get()
                if(result == "true"){
                    Log.i("saveToken", "성공적으로 소비자 토큰을 저장함")
                }
            }else{
                Log.i("saveToken", "토큰저장 실패")
            }
        })

    }

    fun getStoreData(){
        try {
            storeList1!!.clear();
            val content = ""
            val task = SearchTask()
            val result = task.execute(content).get()

            Log.d("받은값", result)


            if(result != null){
                val `object` = JSONObject(result)
                val array = `object`.get("store") as JSONArray

                for(i: Int in 0..array.length()-1){
                    var row = array.getJSONObject(i)

                    storeList1!!.add(
                        StoreData(
                            row.getString("name"),
                            row.getString("pdname"),
                            row.getString("imgUrl")
                        )
                    );
                }
            }
            else {
                Log.d("search", "search fail...")
            }
        } catch (e: Exception) {
        }
        Log.d("search", storeList1?.get(0)?.storename.toString())
    }

    fun getFavoritesData(){
        storeList!!.clear();
        var result = ""
        try{
            Log.d("Favorites List", "Favorites List start...")

            val userId = UserSingleton.getInstance().userId.toString();
            Log.d("앱에서 보낸값", "$userId")

            val task = FavoritesListTask()
            result = task.execute(userId).get()
            Log.d("받은값", result)

            if(result != null){
                val `object` = JSONObject(result)
                val array = `object`.get("store") as JSONArray

                for(i: Int in 0..array.length()-1){
                    var row = array.getJSONObject(i)
                    var storeDTO = StoreDTO()

                    storeDTO.setName(row.getString("name"))
                    storeDTO.setPdname(row.getString("pdname"))
                    storeDTO.setImgUrl(row.getString("imgUrl"))

                    Log.d("storeName : ", storeDTO.getName())
                    Log.d("storePdName : ", storeDTO.getPdname())
                    Log.d("storeUrl : ", storeDTO.getImgUrl())

                    storeList!!.add(StoreData(storeDTO.getName(), storeDTO.getPdname(), storeDTO.getImgUrl()));
                }
            }
            else{
                Log.d("Favorites", "Favorites fail...")
            }

            Log.d("Favorites List", "Favorites List end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
    }

    fun getTodayData(){
        dataList!!.clear();
        var result = ""
        try{
            Log.d("Today List", "Today List start...")

            //val userId = UserSingleton.getInstance().userId.toString();
            //Log.d("앱에서 보낸값", "$userId")

            val task = OnsaleListTask()
            result = task.execute("Y").get()
            Log.d("받은값", result)

            if(result != null){
                val `object` = JSONObject(result)
                val array = `object`.get("post") as JSONArray

                for(i: Int in 0..array.length()-1){
                    var row = array.getJSONObject(i)
                    var onSalePostDTO = OnSalePostDTO()


                    //여기 그냥 그대로 다 보내고 그대로 다 받자
                    onSalePostDTO.setPdName(row.getString("pdName"))
                    onSalePostDTO.setAddress(row.getString("address"))
                    onSalePostDTO.setPrice(row.getInt("price"))
                    onSalePostDTO.setSaleprice(row.getInt("saleprice"))
                    onSalePostDTO.setImg(row.getString("img"))
                    onSalePostDTO.setPdContents(row.getString("pdContents"))
                    onSalePostDTO.setCount(row.getInt("count"))

                    onSalePostDTO.setPdTimer(row.getInt("pdTimer"))
                    onSalePostDTO.setPdid(row.getInt("pdid"))
                    onSalePostDTO.setSellerid(row.getString("sellerid"))
                    onSalePostDTO.setStoreName(row.getString("storeName"))
                    onSalePostDTO.setExpire(row.getString("expire"))
                    onSalePostDTO.setNumber(row.getString("number"))
                    onSalePostDTO.setReviewCount(row.getInt("count"))

                    dataList!!.add(FoodData(
                        onSalePostDTO.getPdName(),
                        onSalePostDTO.getAddress(),
                        onSalePostDTO.getPrice(),
                        onSalePostDTO.getSaleprice(),
                        onSalePostDTO.getImg(),
                        onSalePostDTO.getCount(),
                        onSalePostDTO.getStoreName(),
                        null,
                        0,
                        onSalePostDTO.getPdContents(),
                        null,
                        onSalePostDTO.getPdTimer(),
                        onSalePostDTO.getPdid(),
                        onSalePostDTO.getSellerid(),
                        onSalePostDTO.getExpire(),
                        onSalePostDTO.getNumber()
                    ));
                }
            }
            else{
                Log.d("Onsale", "Onsale fail...")
            }
            Log.d("Onsale List", "Onsale List end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
    }
}
