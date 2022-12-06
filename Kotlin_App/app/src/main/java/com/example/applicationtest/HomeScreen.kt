package com.example.applicationtest
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationtest.DTO.FoodData
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.android.synthetic.main.fragment_st_home.*
import kotlinx.android.synthetic.main.fragment_st_home.listView
import java.io.Serializable

class HomeScreen : Fragment() {

    private lateinit var todayListAdapter: TodayListAdapter
    // 뷰가 생성되었을 때, 프래그먼트와 레이아웃을 연결시켜주는 부분

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var list: ArrayList<FoodData> = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<FoodData>

        Log.e("FirstFragment", "Data List: ${list}")

        // Fragment에서 전달받은 list를 넘기면서 ListAdapter 생성
        todayListAdapter = TodayListAdapter(list)
        listView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        // RecyclerView.adapter에 지정
        listView.adapter = todayListAdapter

        // fragment 리스트에 구분선 넣기
        listView.addItemDecoration(DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL))

        todayListAdapter.setOnItemClickListener(object : TodayListAdapter.OnItemClickListener
        {
            override fun onItemClick(v: View, data: FoodData, pos: Int){
                val intent = Intent(getActivity(), FoodDetailActivity::class.java)
                intent.putExtra("data",data)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                /*Intent(activity, FoodDetailActivity::class.java).apply {
                    putExtra("data",data)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { startActivity(this) }*/
            }
        })
    }
}