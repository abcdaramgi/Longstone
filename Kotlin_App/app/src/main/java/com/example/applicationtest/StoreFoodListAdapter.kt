package com.example.applicationtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class StoreFoodListAdapter (private val context: Context) : RecyclerView.Adapter<StoreFoodListAdapter.ViewHolder>(){

    var datas = mutableListOf<ItemStoreFood>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_store_food_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtFood: TextView = itemView.findViewById(R.id.store_foodlilst_name)
        private val foodImg: ImageView = itemView.findViewById(R.id.img_food_photo)
        private val FoodOCost: TextView = itemView.findViewById(R.id.store_foodlist_ocost)
        private val FoodSCost: TextView = itemView.findViewById(R.id.food_updata_cost)

        fun bind(item: ItemStoreFood) {
            txtFood.text = item.StoreFoodName
            Glide.with(itemView).load(item.StoreFoodImg).into(foodImg)
            FoodOCost.text = item.StoreOCost.toString()
            FoodSCost.text = item.StoreSCost.toString()
        }
    }
}