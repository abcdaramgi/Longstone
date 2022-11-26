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
//같은 bell화면 가게쪽
class StBellAdapter(private val context: Context) : RecyclerView.Adapter<StBellAdapter.ViewHolder>() {

    var datas = mutableListOf<OrderBell>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StBellAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_st_bell_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: StBellAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtFood: TextView = itemView.findViewById(R.id.food_name)
        private val txtCustomer: TextView = itemView.findViewById(R.id.order_customer)
        private val txtDatetime: TextView = itemView.findViewById(R.id.order_time)
        private val txtCost: TextView = itemView.findViewById(R.id.food_cost)

        fun bind(item: OrderBell) { //ItemBell에서 값 가져오기
            //음식 이름
            txtFood.text = item.FoodName
            //고객 이름
            txtCustomer.text = item.Customer
            //주문한 시간
            txtDatetime.text = item.OrderTime.toString()
            //주문 비용
            txtCost.text = item.FoodCost
        }
    }
}