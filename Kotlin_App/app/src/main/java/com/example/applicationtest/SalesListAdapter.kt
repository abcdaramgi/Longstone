package com.example.recyclerview_prac

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationtest.R
import com.example.applicationtest.SalesDetailsScreen

class Second_Recyclerview_Adapter(items: List<SalesDetailsScreen.Recycler_item>) :
    RecyclerView.Adapter<Second_Recyclerview_Adapter.ViewHolder>() {
    var context: Context? = null
    private val items //리사이클러뷰 안에 들어갈 값 저장
            : List<SalesDetailsScreen.Recycler_item>

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val View = LayoutInflater.from(parent.context).inflate(R.layout.item_sales_list, parent, false)
        return ViewHolder(View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.num.setText(items[position].num)
        holder.foodname.setText(items[position].foodname)
        holder.foodcount.setText(items[position].foodcount)
        holder.price.setText(items[position].price)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var num: TextView
        var foodname: TextView
        var foodcount: TextView
        var price: TextView

        init {
            num = itemView.findViewById(R.id.textView_sl_num)
            foodname = itemView.findViewById(R.id.textView_sl_foodname)
            foodcount = itemView.findViewById(R.id.textView_sl_foodcount)
            price = itemView.findViewById(R.id.textView_sl_price)
        }
    }
}