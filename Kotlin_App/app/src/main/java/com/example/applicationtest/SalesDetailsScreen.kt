package com.example.applicationtest

import com.example.applicationtest.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_prac.First_RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_sales_details_screen.*
import kotlinx.android.synthetic.main.activity_sales_details_screen.view.*
import kotlinx.android.synthetic.main.item_sales_details.*
import java.util.Objects


class SalesDetailsScreen : AppCompatActivity() {

    var adapter: First_RecyclerViewAdapter? = null
    var outItems: MutableList<outRecycler_item> = ArrayList()
    private val items: MutableList<Recycler_item> = ArrayList()
    private val aa: MutableList<Objects> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_details_screen)
        val recyclerView = findViewById<RecyclerView>(R.id.st_rv_SalesDetails)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = First_RecyclerViewAdapter(outItems, items)
        recyclerView.adapter = adapter

        //리사이클러뷰 안 리사이클러뷰에 들어갈 데이터
        items.add(Recycler_item("1", "경복궁", "1", "역사"))
        items.add(Recycler_item("2", "창덕궁", "1", "역사"))

        aa.add(items as Objects)

        items.clear()

        items.add(Recycler_item("3", "덕수궁", "2", "역사"))
        items.add(Recycler_item("4", "창경궁", "2", "역사"))

        aa.add(items as Objects)

        //리사이클러뷰 헤더
        outItems.add(outRecycler_item("서울", aa[0] as ArrayList<Recycler_item>))
//        outItems.add(outRecycler_item("부산", aa[1] as MutableList<Recycler_item>))

//        outItems.add(outRecycler_item("서울", items)
//                outItems.add(outRecycler_item("부산", items)
    }

    // 리사이클러뷰 안 리사이클러뷰 아이템구조
    class Recycler_item internal constructor(
        var num: String,
        var foodname: String,
        var foodcount: String,
        var price: String
    )

    class outRecycler_item internal constructor(
        var name: String,
        var innerItem: MutableList<Recycler_item> = ArrayList()
    )
}