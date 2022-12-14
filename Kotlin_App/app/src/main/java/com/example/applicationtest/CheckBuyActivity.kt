package com.example.applicationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.applicationtest.DTO.FoodData
import kotlinx.android.synthetic.main.activity_check_buy.*
import kotlinx.android.synthetic.main.fragment_bell_screen.*
import kotlinx.android.synthetic.main.fragment_bell_screen.toolbar
import java.time.LocalDateTime

class CheckBuyActivity : AppCompatActivity() {
    lateinit var datas : FoodData
    var num : Int = 0
    var sum : Int = 0
    var num2 : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        val datetime: LocalDateTime = LocalDateTime.now()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_buy)

        datas = intent.getSerializableExtra("data") as FoodData
        num = intent.getSerializableExtra("foodCount") as Int
        num2 = datas.updatecost as Int

        sum = num2 * num

        store_name.text = datas.storename
        check_food_name.text = datas.name
        buy_count.text = num.toString() + "개"
        textView15.text = datetime.toString()
        sum_cost.text = sum.toString()+"원"

        setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시
    }

    //액션버튼 클릭
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_go_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
    //커스텀 툴바 액션
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_go_home_menu, menu)
        return true
    }

}