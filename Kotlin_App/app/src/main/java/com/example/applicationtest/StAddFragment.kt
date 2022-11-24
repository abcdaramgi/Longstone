package com.example.applicationtest

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.applicationtest.DTO.postDTO
import com.example.applicationtest.Transport.PostTask
import kotlinx.android.synthetic.main.fragment_st_add.*
import kotlinx.android.synthetic.main.item_alertdialog.view.*

class StAddFragment : AppCompatActivity() {
    private var send_btn: Button? = null
    private var price_edit: EditText? = null
    private var foodName_edit: EditText? = null
    var items: List<postDTO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_st_add)
        timer()

<<<<<<< Updated upstream
        btn_registration.setOnClickListener({
            val builder = AlertDialog.Builder(this)
            builder.setMessage("등록되었습니다.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener{ dialog, id ->
                        finish()
                    })
            builder.show()
        })
        add()
    }
    //NumberPicker 활성화
    fun timer(){
        hour_picker.apply {
            maxValue = 2
            minValue = 0
            wrapSelectorWheel = false
        }
        minute_picker.apply {
            maxValue = 59
            minValue = 0
            wrapSelectorWheel = false
        }
    }
    
    @SuppressLint("MissingInflatedId")
    fun add() {
        send_btn = findViewById(R.id.button)
        price_edit = findViewById(R.id.editTextOrigianlPrice)
        foodName_edit = findViewById(R.id.editTextFoodName)
=======
        send_btn = getView()?.findViewById(R.id.button)
        price_edit = getView()?.findViewById(R.id.editTextOrigianlPrice)
        foodName_edit = getView()?.findViewById(R.id.editTextFoodName)
>>>>>>> Stashed changes

        items = ArrayList()

        send_btn?.run {
            setOnClickListener(View.OnClickListener {
                Log.d("post", "post start...")
                post()
            })
        }
    }
    
    fun post() {
        try {
            val price = price_edit!!.text.toString()
            val foodName = foodName_edit!!.text.toString()

            Log.d("앱에서 보낸값", "$price, $foodName")
            val task = PostTask()
            val result = task.execute(price, foodName).get()
            Log.d("받은값", result)
            Log.d("post", "posting end...")
        } catch (e: Exception) {
        }
    }

    companion object {
        fun newInstance():StAddFragment {
            return StAddFragment()
        }
    }
}