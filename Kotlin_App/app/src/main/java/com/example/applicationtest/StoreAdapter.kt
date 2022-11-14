package com.example.applicationtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class StoreAdapter(private var list: MutableList<StoreData>): RecyclerView.Adapter<StoreAdapter.ListItemViewHolder> () {

    // inner class로 ViewHolder 정의
    inner class ListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var name: TextView = itemView!!.findViewById(R.id.store_de_name)
        var place: TextView = itemView!!.findViewById(R.id.store_de_place)
        var imgFood: ImageView = itemView!!.findViewById(R.id.img_store_photo)

        // onBindViewHolder의 역할을 대신한다.
        fun bind(data: StoreData, position: Int) {
            //Log.d("ListAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====")
            //Log.d("ListAdapter", data.getData1() + " " + data.getData2() + " " + data.getData3())
            Glide.with(itemView).load(data.getData3()).into(imgFood)
            name.text = data.getData1()
            place.text = data.getData2()

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
        fun onItemClick(v: View, data: StoreData, pos: Int)
    }

    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // ViewHolder에게 item을 보여줄 View로 쓰일 item_data_list.xml를 넘기면서 ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store_view, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: StoreAdapter.ListItemViewHolder, position: Int) {
        Log.d("ListAdapter", "===== ===== ===== ===== onBindViewHolder ===== ===== ===== =====")
        holder.bind(list[position], position)
    }

}