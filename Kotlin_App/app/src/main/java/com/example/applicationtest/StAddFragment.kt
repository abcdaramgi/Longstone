package com.example.applicationtest

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.applicationtest.DTO.postDTO
import com.example.applicationtest.Transport.PostTask
import kotlinx.android.synthetic.main.fragment_st_add.*
import kotlinx.android.synthetic.main.item_alertdialog.view.*
import java.io.ByteArrayOutputStream
import java.io.InputStream

class StAddFragment : AppCompatActivity() {
    private var send_btn: Button? = null
    private var original_price_edit: EditText? = null
    private var discount_price_edit: EditText? = null
    private var foodName_edit: EditText? = null
    private var food_count: EditText? = null
    private var img_btn: Button? = null
    var items: List<postDTO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_st_add)
        timer()
        img_add()
        add()

        btn_registration.setOnClickListener({
            val builder = AlertDialog.Builder(this)
            builder.setMessage("등록되었습니다.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener{ dialog, id ->
                        finish()
                    })
            builder.show()
        })
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
        send_btn = findViewById(R.id.btn_registration)
        original_price_edit = findViewById(R.id.editTextOrigianlPrice)
        foodName_edit = findViewById(R.id.editTextFoodName)
        discount_price_edit = findViewById((R.id.editTextDiscountPrice))
        food_count = findViewById((R.id.editTextNumber))

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
            val originalPrice = original_price_edit!!.text.toString()
            var discountPrice = discount_price_edit!!.text.toString()
            var foodCount = food_count!!.text.toString()
            val foodName = foodName_edit!!.text.toString()

            Log.d("앱에서 보낸값", "$originalPrice, $discountPrice, $foodCount $foodName")
            val task = PostTask()
            val result = task.execute(originalPrice, discountPrice, foodCount, foodName).get()
            Log.d("받은값", result)
            Log.d("post", "posting end...")

        } catch (e: Exception) {
        }
    }

    fun img_add() {
        //이미지등록
        img_btn = findViewById(R.id.button2)
        img_btn?.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery(){
        Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            startActivityForResult(
                Intent.createChooser(this, "Get Album"),
                102
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (requestCode == 102 && resultCode == Activity.RESULT_OK){
            var currentImageURL = intent?.data
            // Base64 인코딩부분
            val ins: InputStream? = currentImageURL?.let {
                applicationContext?.contentResolver?.openInputStream(
                    it
                )
            }
            val img: Bitmap = BitmapFactory.decodeStream(ins)
            ins?.close()
            val resized = Bitmap.createScaledBitmap(img, 256, 256, true)
            val byteArrayOutputStream = ByteArrayOutputStream()
            resized.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            val outStream = ByteArrayOutputStream()
            val res: Resources = resources
            var profileImageBase64 = Base64.encodeToString(byteArray, Base64.NO_WRAP)
            // 여기까지 인코딩 끝

            // 이미지 뷰에 선택한 이미지 출력
            val imageview: ImageView?= findViewById(R.id.imageView)
            imageview?.setImageURI(currentImageURL)
            try {
                //이미지 선택 후 처리
            }catch (e: Exception){
                e.printStackTrace()
            }
        } else{
            Log.d("ActivityResult", "something wrong")
        }
    }

    companion object {
        fun newInstance():StAddFragment {
            return StAddFragment()
        }
    }
}