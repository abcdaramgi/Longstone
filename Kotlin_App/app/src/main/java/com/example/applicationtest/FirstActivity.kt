package com.example.applicationtest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationtest.Singleton.PreferenceUtil
import com.example.applicationtest.Singleton.SellerSingleton
import com.example.applicationtest.Singleton.UserSingleton
import com.example.applicationtest.Transport.LoginTask
import com.example.applicationtest.Transport.SellerLoginTask
import com.google.android.gms.common.util.SharedPreferencesUtils
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_menu_view.*


class FirstActivity : AppCompatActivity() {
    private var login_id : EditText? = null
    private var login_pw : EditText? = null
    private var login_btn : Button? = null
//    lateinit var prefs: PreferenceUtil

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
//        prefs = PreferenceUtil(applicationContext)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_first)

        //소비자 이동
        btn_login.setOnClickListener {
//            Log.d("Login", "login start...")
//            val result = login()
//            Log.d("result = ", result)
//            if (result == "true") {
//
//                val id = login_id?.text.toString()
//                val pw = login_pw?.text.toString()
//
//                MyApplication.prefs.setString("id", id)
//                MyApplication.prefs.setString("pw", pw)
//
////                preferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
////                val editor = preferences.edit()
////                editor.putString("id", login_id!!.text.toString())
////                editor.putString("pw", login_pw!!.text.toString())
////                editor.commit()
////                val testid = MyApplication.prefs.getString("id", "0")
////                val testpw = MyApplication.prefs.getString("pw", "0")
//
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//            } else {
//                Log.d("Login", "login fail...")
//            }
            //테스트시 이부분 주석풀고 위쪽 코드 주석달고 테스트
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //판매자 이동
        btn_store.setOnClickListener {

            //===================================================================//
            //판매자 로그인 부분
            //===================================================================//
            Log.d("SellerLogin", "login start...")
            val result = sellerLogin()
            Log.d("result = ", result)
            if(result == "true"){
                val intent = Intent(this, StMainActivity::class.java)
                startActivity(intent)
            }else{
                Log.d("SellerLogin", "login fail...")
            }
            //===================================================================//

//            val intent = Intent(this, StMainActivity::class.java)
//            startActivity(intent)
        }
        //회원가입
        btn_register.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    //사용자 로그인
    fun login() : String{
        var result = ""
        try{
            login_id = findViewById(R.id.edit_id)
            login_pw = findViewById(R.id.edit_pw)
            login_btn = findViewById(R.id.btn_login)

            val id = login_id!!.text.toString()
            val pw = login_pw!!.text.toString()

            Log.d("앱에서 보낸값", "$id, $pw")

            val task = LoginTask()
            result = task.execute(id,pw).get()

            Log.d("받은값", result)
            //사용자 로그인 성공시 사용자의 ID를 싱글톤 객체안에 넣는다
            if(result == "true"){
                UserSingleton.getInstance().userId = login_id!!.text.toString();
            }
            Log.d("Login", "login end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
        return result
    }

    //판매자 로그인
    fun sellerLogin() : String{
        var result = ""
        try{
            login_id = findViewById(R.id.edit_id)
            login_pw = findViewById(R.id.edit_pw)
            login_btn = findViewById(R.id.btn_login)

            val id = login_id!!.text.toString()
            val pw = login_pw!!.text.toString()

            Log.d("앱에서 보낸값", "$id, $pw")

            val task = SellerLoginTask()
            result = task.execute(id,pw).get()

            Log.d("받은값", result)

            //판매자 로그인 성공시 판매자의 ID를 싱글톤 객체안에 넣는다
            if(result == "true"){
                SellerSingleton.getInstance().sellerId = login_id!!.text.toString();
            }
            Log.d("SellerLogin", "login end...")
        }catch(e : Exception){
            e.printStackTrace()
        }
        return result
    }
}