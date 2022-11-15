package com.example.applicationtest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_st_home.*

class StHomeFragment : Fragment()
{
    //RecyclerView.adapter에 지정할 Adapter
    private  lateinit var listAdapter: ListAdapter
    // 뷰가 생성되었을 때, 프래그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃과 조각을 서로 연결
        val view = inflater.inflate(R.layout.fragment_st_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list: ArrayList<FoodData> = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<FoodData>
        Log.e("FirstFragment", "Data List: ${list}")

        // Fragment에서 전달받은 list를 넘기면서 ListAdapter 생성
        listAdapter = ListAdapter(list)
        listView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        // RecyclerView.adapter에 지정
        listView.adapter = listAdapter
    }
}