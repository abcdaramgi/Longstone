package com.example.recyclerview_prac

import com.example.applicationtest.R
import android.animation.ValueAnimator
import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationtest.SalesDetailsScreen

class First_RecyclerViewAdapter(private val listData: List<SalesDetailsScreen.outRecycler_item>, items: List<SalesDetailsScreen.Recycler_item>
) :
    RecyclerView.Adapter<First_RecyclerViewAdapter.ViewHolder>() {
    // 리사이클러뷰 안 리사이클러뷰 관련
    // 두 번째 어댑터와 연결
    var adapter: Second_Recyclerview_Adapter? = null
    private val items // 메인 엑티비티에 만든 class 두 번째 어댑터에 보내기 위함
            : List<SalesDetailsScreen.Recycler_item>
    private var context: Context? = null

    // 리사이클러뷰 접고 펴기 위한 변수
    // Item의 클릭 상태를 저장할 array 객체
    private val selectedItems = SparseBooleanArray()

    // 직전에 클릭됐던 Item의 position
    var prePosition = -1

    // 메인액티비티와 연결
    init {
        this.items = items
    }

    // 뷰홀더가 생성되는 곳
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sales_details, parent, false)
        return ViewHolder(v)
    }

    // 생성된 뷰홀더에 데이터 넣는 함수
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //리사이클러뷰 넣는 부분
        holder.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = Second_Recyclerview_Adapter(items) // 메인에서 받아온 items를 두 번째 리사이클러뷰 어댑터로 넘기기
        holder.recyclerView.adapter = adapter
        holder.onBind(position)
        holder.textView1.text = listData[position].name // textView 데이터 설정
        holder.textView1.setOnClickListener {
            if (selectedItems[position]) {
                // 펼쳐진 Item을 클릭 시
                selectedItems.delete(position)
            } else {
                // 직전의 클릭됐던 Item의 클릭상태를 지움
                selectedItems.delete(prePosition)
                // 클릭한 Item의 position을 저장
                selectedItems.put(position, true)
            }
            // 해당 포지션의 변화를 알림
            if (prePosition != -1) notifyItemChanged(prePosition)
            notifyItemChanged(position)
            // 클릭된 position 저장
            prePosition = position
        }
    }

    // 총 갯수
    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 첫번째 아이템에서 사용했던 텍스트뷰와 리사이클러뷰
        val textView1: TextView
        val recyclerView: RecyclerView
        var positions: Int = 0

        init {
            textView1 = itemView.findViewById(R.id.main_item_name)
            recyclerView = itemView.findViewById(R.id.second_recyclerview)
        }

        fun onBind(position: Int) {
            this.positions = position
            changeVisibility(selectedItems[position])
        }

        // 리사이클러뷰가 접히고 펼쳐질때 애니메이션 효과
        private fun changeVisibility(isExpanded: Boolean) {
            // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
            val dpValue = 250
            val d = context!!.resources.displayMetrics.density
            val height = (dpValue * d).toInt()

            // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
            val va =
                if (isExpanded) ValueAnimator.ofInt(0, height) else ValueAnimator.ofInt(height, 0)
            // Animation이 실행되는 시간, n/1000초
            va.duration = 600
            va.addUpdateListener { animation -> // value는 height 값
                val value = animation.animatedValue as Int
                // imageView의 높이 변경
                recyclerView.layoutParams.height = value
                recyclerView.requestLayout()
                // imageView가 실제로 사라지게하는 부분
                recyclerView.visibility =
                    if (isExpanded) View.VISIBLE else View.GONE
            }
            // Animation start
            va.start()
        }
    }
}