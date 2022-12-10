package com.example.applicationtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.applicationtest.DTO.FoodData

//ST_HOME_LIST, ITEM 연결
class ListAdapter(private var list: ArrayList<FoodData>): RecyclerView.Adapter<ListAdapter.ListItemViewHolder> () {

    // inner class로 ViewHolder 정의
    inner class ListItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {

        var data1Text: TextView = itemView!!.findViewById(R.id.food_name)
        var data2Text: TextView = itemView!!.findViewById(R.id.food_place)
        var data3Text: TextView = itemView!!.findViewById(R.id.food_cost)
        var imgFood: ImageView = itemView!!.findViewById(R.id.img_store_food_photo)
        var switch: Switch = itemView!!.findViewById(R.id.switch1)
        var button3: Button = itemView!!.findViewById(R.id.button3)


        // onBindViewHolder의 역할을 대신한다.
        fun bind(data: FoodData, position: Int) {
            Log.d("ListAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====")
            Log.d("ListAdapter", data.getName()+" "+data.getPlace()+" "+data.getCost())
            data1Text.text = data.getName()
            data2Text.text = data.getPlace()
            data3Text.text = data.getUpdatecost().toString() + "원"
            if (data.getStatus() == "Y") {
                switch.isChecked = true
            }
            Glide.with(itemView).load(data.getImg()).into(imgFood)
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

        holder.switch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener{
                buttonView, isChecked ->
            if (holder.switch.isChecked) {
                Log.d("스위치", "켜짐")
            }
            else{
                Log.d("스위치", "꺼짐")
            }
        })

        holder.button3.setOnClickListener(View.OnClickListener { //Position for remove
            val position = holder.layoutPosition
            notifyItemRemoved(position)

//            var params :String = holder.data1Text.text.toString()
//            var result = ""
//            val task = deleteFoodTask()
//            result = task.execute(params).get()
//            Log.d("받은값", result)
        })
    }

}