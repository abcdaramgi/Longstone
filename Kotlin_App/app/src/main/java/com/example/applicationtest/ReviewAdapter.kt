package com.example.applicationtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ReviewAdapter (private val context: Context) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    var datas = mutableListOf<ItemReview>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_review_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtScore: TextView = itemView.findViewById(R.id.textView11)
        private val txtReview: TextView = itemView.findViewById(R.id.textView13)
        private val reImg : ImageView = itemView.findViewById(R.id.imageView6)

        fun bind(item: ItemReview) {
            txtScore.text = item.score.toString() + "점"
            txtReview.text = item.review
            Glide.with(itemView).load(item.review_img).into(reImg)
        }
    }
}