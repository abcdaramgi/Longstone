package com.example.applicationtest

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TodayListAdapter(private var list: MutableList<FoodData>): RecyclerView.Adapter<TodayListAdapter.ListItemViewHolder> () {

    // inner class로 ViewHolder 정의
    inner class ListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var name: TextView = itemView!!.findViewById(R.id.food_name)
        var place: TextView = itemView!!.findViewById(R.id.food_place)
        var cost: TextView = itemView!!.findViewById(R.id.food_cost)
        var imgFood: ImageView = itemView!!.findViewById(R.id.img_food_photo)
        var cost2: TextView = itemView!!.findViewById(R.id.food_updata_cost)
        // onBindViewHolder의 역할을 대신한다.

        fun bind(data: FoodData, position: Int) {
            //Log.d("ListAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====")
            Log.d("ListAdapter", data.getData1() + " " + data.getData2() + " " + data.getData3())
            Glide.with(itemView).load(data.getData4()).into(imgFood)
            name.text = data.getData1()
            place.text = data.getData2()
            cost.text = data.getData3().toString() + "원"
            cost2.text = data.getData5().toString() + "원"

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,data,pos)
                }
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(v:View, data: FoodData, pos : Int)
    }

    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    // ViewHolder에게 item을 보여줄 View로 쓰일 item_data_list.xml를 넘기면서 ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_today_food_view, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: TodayListAdapter.ListItemViewHolder, position: Int) {
        Log.d("ListAdapter", "===== ===== ===== ===== onBindViewHolder ===== ===== ===== =====")
        holder.bind(list[position], position)
    }
}