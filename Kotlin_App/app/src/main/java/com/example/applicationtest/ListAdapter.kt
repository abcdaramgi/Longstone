package com.example.applicationtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//ST_HOME_LIST, ITEM 연결
class ListAdapter(private var list: ArrayList<FoodData>): RecyclerView.Adapter<ListAdapter.ListItemViewHolder> () {

    // inner class로 ViewHolder 정의
    inner class ListItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {

        var data1Text: TextView = itemView!!.findViewById(R.id.food_name)
        var data2Text: TextView = itemView!!.findViewById(R.id.food_place)
        var data3Text: TextView = itemView!!.findViewById(R.id.food_cost)


        // onBindViewHolder의 역할을 대신한다.
        fun bind(data: FoodData, position: Int) {
            Log.d("ListAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====")
            Log.d("ListAdapter", data.getData1()+" "+data.getData2()+" "+data.getData3())
            data1Text.text = data.getData1()
            data2Text.text = data.getData2()
            data3Text.text = data.getData3().toString()
        }
    }

    // ViewHolder에게 item을 보여줄 View로 쓰일 item_data_list.xml를 넘기면서 ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_view, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: ListAdapter.ListItemViewHolder, position: Int) {
        Log.d("ListAdapter", "===== ===== ===== ===== onBindViewHolder ===== ===== ===== =====")
        holder.bind(list[position], position)
    }

}