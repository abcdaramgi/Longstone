package com.example.applicationtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
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
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, data: StoreData, pos: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // ViewHolder에게 item을 보여줄 View로 쓰일 item_data_list.xml를 넘기면서 ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_store_view, parent, false)
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

class StoreAdapter1(private var list: MutableList<StoreData>):
    RecyclerView.Adapter<StoreAdapter1.ListItemViewHolder> (), Filterable {

    var filterStore = ArrayList<StoreData>()
    var itemFilter = ItemFilter()

    init {
        filterStore.addAll(list)
    }

    override fun getFilter(): Filter {
        return itemFilter
    }

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
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, data: StoreData, pos: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // ViewHolder에게 item을 보여줄 View로 쓰일 item_data_list.xml를 넘기면서 ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_store_view, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filterStore.count()
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: StoreAdapter1.ListItemViewHolder, position: Int) {
        Log.d("ListAdapter", "===== ===== ===== ===== onBindViewHolder ===== ===== ===== =====")
        holder.bind(filterStore[position], position)
    }

    inner class ItemFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<StoreData> = ArrayList<StoreData>()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = list
                results.count = list.size

                return results
                //공백제외 2글자 이하인 경우 -> 이름으로만 검색
            } else if (filterString.trim { it <= ' ' }.length <= 2) {
                for (store in list) {
                    if (store.storename?.contains(filterString) == true ||
                        store.storeinfo?.contains(
                            filterString
                        ) == true
                    ) {
                        filteredList.add(store)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                filterStore.clear()
                filterStore.addAll(filterResults.values as ArrayList<StoreData>)
                notifyDataSetChanged()
        }
    }
}