package com.example.applicationtest
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class OrderAdapter(private val context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    var datas = mutableListOf<ItemOrder>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtUsername: TextView = itemView.findViewById(R.id.text_userName)
        private val txtOrdermenu: TextView = itemView.findViewById(R.id.text_orderMenu)
        private val txtMenucount: TextView = itemView.findViewById(R.id.text_menuCount)
        private val txtOrderdate: TextView = itemView.findViewById(R.id.text_orderDate)
        private val txtCost: TextView = itemView.findViewById(R.id.text_orderCost)

        fun bind(item: ItemOrder) { //ItemBell에서 값 가져오기
            //고객 이름
            txtUsername.text = item.username
            //음식 이름
            txtOrdermenu.text = item.ordermenu
            //음식 갯수
            txtMenucount.text = item.menucount
            //주문한 시간
            txtOrderdate.text = item.orderdate
            //주문 비용
            txtCost.text = item.ordercost
        }
    }
}