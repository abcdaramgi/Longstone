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

class SignupActivity : AppCompatActivity(), Runnable {
    private var send_btn: Button? = null

    private var id_edit: EditText? = null
    private var pw_edit: EditText? = null
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
        pw_edit = findViewById(R.id.editTextTextPassword)
        name_edit = findViewById(R.id.editTextTextPersonName)
        email_edit = findViewById(R.id.editTextTextEmailAddress)
        phone_edit = findViewById(R.id.editTextPhone)
        birth_edit = findViewById(R.id.editTextDate)

        items = ArrayList()

        send_btn?.run {
            setOnClickListener(View.OnClickListener {
                Log.d("Register", "register start...")
                register()
            })
        }
    }
    fun register() {
        try {
            val id = name_edit!!.text.toString()
            val pw = pw_edit!!.text.toString()
            val name = name_edit!!.text.toString()
            val email = email_edit!!.text.toString()
            val phone = phone_edit!!.text.toString()
            val birth = birth_edit!!.text.toString()
            Log.d("앱에서 보낸값", "$id, $pw, $name, $email, $phone, $birth")
            val task = RegisterTask()
            val result = task.execute(id, pw, name, email, phone, birth).get()
            Log.d("받은값", result)
            Log.d("Register", "register end...")
        } catch (e: Exception) {
        }
    }

    //뭔가 나중에 쓸거같음 낸중에 지울게
    override fun run() {

    }
}