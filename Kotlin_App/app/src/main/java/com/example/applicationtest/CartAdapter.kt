package com.example.applicationtest

import android.content.Context
import android.content.Intent
import android.media.Image
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_cart_screen.*
import kotlinx.android.synthetic.main.item_cart_view.view.*


class CartAdapter (private val context: Context) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    var datas = mutableListOf<ItemCart>()
    val checkboxStatus = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cart_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val txtFood: TextView = itemView.findViewById(R.id.data3Text)
        private val txtStore: TextView = itemView.findViewById(R.id.data2Text)
        private val foodImg : ImageView = itemView.findViewById(R.id.imageView3)
        private val foodcount : TextView = itemView.findViewById(R.id.textView12)
        val checkBox : CheckBox = itemView.findViewById(R.id.checkBox2)

        fun bind(item: ItemCart) {
            checkBox.isChecked = item.getData1()
            txtFood.text = item.FoodName
            txtStore.text = item.StoreName
            Glide.with(itemView).load(item.food_img).into(foodImg)
            foodcount.text = item.food_count.toString()

            checkBox.setOnClickListener(View.OnClickListener { v ->
                val cb = v as CheckBox
                datas.get(position).setData1(cb.isChecked)

                /*Toast.makeText(
                    v.getContext(),
                    "Clicked on Checkbox: " + cb.getText() + " is "
                            + cb.isChecked(), Toast.LENGTH_LONG).show();*/
            })
        }
    }
}

class BuyListAdapter(private val context: Context) : RecyclerView.Adapter<BuyListAdapter.ViewHolder>() {
    var data = mutableListOf<BuyListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_buy_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        holder.button.setOnClickListener{
            val intent = Intent(holder.button?.context,WriteReviewActivity::class.java)
            ContextCompat.startActivity(holder.button.context, intent, null)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtfdName: TextView = itemView.findViewById(R.id.buy_list_fd_name)
        private val txtstName: TextView = itemView.findViewById(R.id.buy_list_st_name)
        private val txtfdCount: TextView = itemView.findViewById(R.id.buy_list_fd_count)
        private val txtImg: ImageView = itemView.findViewById(R.id.buy_list_fd_img)
        val button : Button = itemView.findViewById(R.id.buy_list_bt)

        fun bind(item: BuyListItem) {
            txtstName.text = item.buyliste_st_name
            txtfdName.text = item.buyliste_fd_name
            txtfdCount.text = item.buyliste_fd_count + "개"
            Glide.with(itemView).load(item.buylist_img).into(txtImg)
        }

    }
}

