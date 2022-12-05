package com.example.applicationtest

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Base64
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationtest.DTO.postDTO
import com.example.applicationtest.Singleton.SellerSingleton
import com.example.applicationtest.Transport.FileUploadUtils
import com.example.applicationtest.Transport.PostTask
import kotlinx.android.synthetic.main.fragment_st_add.*
import kotlinx.android.synthetic.main.item_alertdialog.view.*
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class StAddFragment : AppCompatActivity() {
    private var send_btn: Button? = null

    private var original_price_edit: EditText? = null
    private var discount_price_edit: EditText? = null
    private var foodName_edit: EditText? = null
    private var food_count: EditText? = null
    private var hourPicker : NumberPicker? = null
    private var minutePicker :NumberPicker? = null
    private var imageview : ImageView? = null

    var imgVwSelected: ImageView? = null
    var btnImageSend: Button? = null
    var btnImageSelection:Button? = null
    var tempSelectFile: File? = null

    private var img_btn: Button? = null
    var items: List<postDTO>? = null

//    fun img_add() {
//        //이미지등록
//        img_btn = findViewById(R.id.button2)
//        img_btn?.setOnClickListener {
//            openGallery()
//        }
//    }
//    @SuppressLint("MissingInflatedId")
//    fun add() {
//        send_btn = findViewById(R.id.btn_registration)
//        original_price_edit = findViewById(R.id.editTextOrigianlPrice)
//        foodName_edit = findViewById(R.id.editTextFoodName)
//        discount_price_edit = findViewById((R.id.editTextDiscountPrice))
//        food_count = findViewById((R.id.editTextNumber))
//        hourPicker = findViewById(R.id.hour_picker)
//        minutePicker = findViewById(R.id.minute_picker)
//
//        items = ArrayList()
//
//        send_btn?.run {
//            setOnClickListener(View.OnClickListener {
//                Log.d("post", "post start...")
//                post()
//            })
//        }
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_st_add)
        timer()
//        img_add()
//        add()
        send_btn = findViewById(R.id.btn_registration)
        original_price_edit = findViewById(R.id.editTextOrigianlPrice)
        foodName_edit = findViewById(R.id.editTextFoodName)
        discount_price_edit = findViewById((R.id.editTextDiscountPrice))
        food_count = findViewById((R.id.editTextNumber))
        hourPicker = findViewById(R.id.hour_picker)
        minutePicker = findViewById(R.id.minute_picker)
        imageview = findViewById(R.id.imageView)

        items = ArrayList()

        button2.setOnClickListener {
            openGallery()
        }

        btn_registration.setOnClickListener({
            post()
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

//    @SuppressLint("MissingInflatedId")
//    fun add() {
//        send_btn = findViewById(R.id.button)
//        price_edit = findViewById(R.id.editTextOrigianlPrice)
//        foodName_edit = findViewById(R.id.editTextFoodName)
//    }

    fun post() {

        if(imageview!!.drawable != null){ //이미지 뷰에 이미지가 올라갔을때
            Log.d("imagePost", "imagePost start...")
            FileUploadUtils.send2Server(tempSelectFile)
            Log.d("imagePost", "imagePost end...")
        }
        else{ //이미지뷰에 아무것도 없을때
            Log.d("Debug", "imageView is null...")
        }

        try {
            Log.d("post", "post start...")
            val originalPrice = original_price_edit!!.text.toString()
            var discountPrice = discount_price_edit!!.text.toString()
            var foodCount = food_count!!.text.toString()
            val foodName = foodName_edit!!.text.toString()
            var timePicker = ((hourPicker!!.value * 60) + (minutePicker!!.value)).toString()

            Log.d("앱에서 보낸값", "${SellerSingleton.getInstance().sellerId} ,$originalPrice, " +
                    "$discountPrice, $foodCount, $foodName, $timePicker")
            val task = PostTask()
            val result = task.execute(originalPrice, discountPrice, foodCount, foodName, timePicker).get()
            Log.d("받은값", result)
            Log.d("post", "posting end...")

        } catch (e: Exception) {
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

            val instream : InputStream? = contentResolver.openInputStream(currentImageURL!!)
            val image = BitmapFactory.decodeStream(instream)

            // Base64 인코딩부분
//            val ins: InputStream? = currentImageURL?.let {
//                applicationContext?.contentResolver?.openInputStream(
//                    it
//                )
//            }
//            val img: Bitmap = BitmapFactory.decodeStream(ins)
//            ins?.close()
//            val resized = Bitmap.createScaledBitmap(img, 256, 256, true)
//            val byteArrayOutputStream = ByteArrayOutputStream()
//            resized.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
//            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
//            val outStream = ByteArrayOutputStream()
//            val res: Resources = resources
//            var profileImageBase64 = Base64.encodeToString(byteArray, Base64.NO_WRAP)
            // 여기까지 인코딩 끝

            // 이미지 뷰에 선택한 이미지 출력
//            val imageview: ImageView?= findViewById(R.id.imageView)
//            imageview?.setImageURI(currentImageURL)
            try {
                //이미지 선택 후 처리
                val `in` = contentResolver.openInputStream(currentImageURL)
                val image = BitmapFactory.decodeStream(`in`)
                imageview!!.setImageBitmap(image)
                `in`!!.close()

                val date: String = SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Date())

                tempSelectFile = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(),
                    "temp_" + date + ".jpeg"
                )
                val out: OutputStream = FileOutputStream(tempSelectFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, out)
//                FileUploadUtils.send2Server(tempSelectFile)
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