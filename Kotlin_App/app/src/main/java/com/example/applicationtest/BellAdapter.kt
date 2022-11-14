package com.example.applicationtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BellAdapter (private val context: Context) : RecyclerView.Adapter<BellAdapter.ViewHolder>() {

    var datas = mutableListOf<ItemBell>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_bell_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.st_name)
        private val txtInfo: TextView = itemView.findViewById(R.id.st_info)

        fun bind(item: ItemBell) {
            txtName.text = item.storeName
            txtInfo.text = item.storeInfo
        }
    }
}