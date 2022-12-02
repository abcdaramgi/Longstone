package com.example.applicationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.fragment_bell_screen.*
import kotlinx.android.synthetic.main.item_today_food_detail.*

class ReviewActivity : AppCompatActivity() {
    lateinit var reviewAdapter: ReviewAdapter
    val datas = mutableListOf<ItemReview>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        var result : Float = 0.0F

        /*setSupportActionBar(toolbar) //커스텀한 toolbar 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //액션바 뒤로가기 아이콘 표시*/

        initRecycler()
        re_review.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        for(i : Int in 0..datas.size-1)
        {
            result += datas[i].score.toFloat()
        }
        ratingBar.rating = (result/datas.size).toFloat()
        sum_re.text = "%.1f점".format((result/datas.size).toFloat())
        re_count.text = datas.size.toString() + "개"
    }

    //액션바의 뒤로가기 버튼 클릭 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            //뒤로가기 버튼이 눌리면 현재 접속 중인 화면을 나감
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecycler() {
        reviewAdapter = ReviewAdapter(this)
        re_review.adapter = reviewAdapter


        datas.apply {
            add(ItemReview(5,"바삭하고 맛있어요.",R.drawable.image_bread1))
            add(ItemReview(4,"바삭하고 맛있어요.",R.drawable.image_bread1))
            add(ItemReview(5,"바삭하고 맛있어요.",R.drawable.image_bread1))
        }
        reviewAdapter.datas = datas
        reviewAdapter.notifyDataSetChanged()
    }
}