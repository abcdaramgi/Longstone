package com.example.applicationtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationtest.DTO.MemberDTO
import com.example.applicationtest.Transport.RegisterTask
import com.example.applicationtest.Transport.SearchTask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type


class StoreData2(
    var name: String? =null,
    var number: String? =null,
    var pdname: String? =null,
): Serializable {
    fun getData1(): String? {
        return name
    }
    fun setData1(name: String) {
        this.name = name
    }
    fun getData2(): String? {
        return number
    }
    fun setData2(address: String) {
        this.number = number
    }
    fun getData3(): String? {
        return pdname
    }
    fun setData3(type: String) {
        this.pdname = pdname
    }
}

class SignupActivity : AppCompatActivity(), Runnable {
    private var send_btn: Button? = null

    private var id_edit: EditText? = null
    private var pw_edit: EditText? = null
    private var nickname_edit: EditText? = null
    private var name_edit: EditText? = null
    private var email_edit: EditText? = null
    private var phone_edit: EditText? = null
    private var birth_edit: EditText? = null

    var list: ListView? = null
    var items: List<MemberDTO>? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        //버튼, list를 받아온다
        send_btn = findViewById(R.id.button_signup)

        id_edit = findViewById(R.id.editTextID)
        pw_edit = findViewById(R.id.editTextPassword)
        nickname_edit = findViewById(R.id.editTextNickname)
        name_edit = findViewById(R.id.editTextPersonName)
        email_edit = findViewById(R.id.editTextEmailAddress)
        phone_edit = findViewById(R.id.editTextPhone)
        birth_edit = findViewById(R.id.editTextDate)

        items = ArrayList()

        send_btn?.run {
            setOnClickListener(View.OnClickListener {
                Log.d("Register", "register start...")
                register()
                //testplz()
            })
        }
    }
    fun register() {
        try {
            val id = name_edit!!.text.toString()
            val pw = pw_edit!!.text.toString()
            val nickname = nickname_edit!!.text.toString()
            val name = name_edit!!.text.toString()
            val email = email_edit!!.text.toString()
            val phone = phone_edit!!.text.toString()
            val birth = birth_edit!!.text.toString()
            Log.d("앱에서 보낸값", "$id, $pw, $nickname, $name, $email, $phone, $birth")
            val task = RegisterTask()
            val result = task.execute(id, pw, nickname, name, email, phone, birth).get()
            Log.d("받은값", result)
            Log.d("Register", "register end...")
        } catch (e: Exception) {
        }
    }

    fun testplz(){
        try {
            val content = id_edit!!.text.toString()
            val task = SearchTask()
            val result = task.execute(content).get()

            val gson = Gson()
            val listType: Type = object : TypeToken<ArrayList<StoreData2?>?>() {}.type
            val yourClassList: List<StoreData2> = Gson().fromJson(result, listType)

            Log.d("받은값", result)
            Log.d("Register", "register end...")
            Log.d("리스트변환했냐", yourClassList.get(1).name.toString())
        } catch (e: Exception) {
        }
    }

    //뭔가 나중에 쓸거같음 낸중에 지울게
    override fun run() {

    }
}