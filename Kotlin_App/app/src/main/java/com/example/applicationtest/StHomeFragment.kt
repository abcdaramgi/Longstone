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

class HomeData(
    private var data1: String? =null,
    private var data2: String? =null,
    private var data3: String? =null,
) {
    fun getData1(): String? {
        return data1
    }
    fun setData1(name: String) {
        this.data1 = data1
    }
    fun getData2(): String? {
        return data2
    }
    fun setData2(address: String) {
        this.data2 = data2
    }
    fun getData3(): String? {
        return data3
    }
    fun setData3(type: String) {
        this.data3 = data3
    }
}
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

        var list: ArrayList<HomeData> = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<HomeData>
        Log.e("FirstFragment", "Data List: ${list}")

        // Fragment에서 전달받은 list를 넘기면서 ListAdapter 생성
        listAdapter = ListAdapter(list)
        listView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        // RecyclerView.adapter에 지정
        listView.adapter = listAdapter
    }
}