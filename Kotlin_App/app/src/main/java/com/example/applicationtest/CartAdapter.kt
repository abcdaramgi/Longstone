package com.example.applicationtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_cart_screen.*

class CartAdapter (private val context: Context) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    var datas = mutableListOf<ItemCart>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cart_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtFood: TextView = itemView.findViewById(R.id.data3Text)
        private val txtStore: TextView = itemView.findViewById(R.id.data2Text)
        private val foodImg : ImageView = itemView.findViewById(R.id.imageView3)

        fun bind(item: ItemCart) {
            txtFood.text = item.FoodName
            txtStore.text = item.StorePlace
            Glide.with(itemView).load(item.food_img).into(foodImg)
        }
    }
}