package com.example.applicationtest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationtest.DTO.FoodData
import kotlinx.android.synthetic.main.fragment_st_home.listView

class HomeScreen : Fragment() {

    private lateinit var onsaleListAdapter: OnsaleListAdapter
    var list: MutableList<FoodData> = ArrayList()
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

        list = requireActivity().intent!!.extras!!.get("DataList") as MutableList<FoodData>

        Log.e("FirstFragment", "Data List: ${list}")

        // Fragment에서 전달받은 list를 넘기면서 ListAdapter 생성
        onsaleListAdapter = OnsaleListAdapter(list)
        listView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        // RecyclerView.adapter에 지정
        listView.adapter = onsaleListAdapter

        // fragment 리스트에 구분선 넣기
        listView.addItemDecoration(DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL))

        onsaleListAdapter.setOnItemClickListener(object : OnsaleListAdapter.OnItemClickListener
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