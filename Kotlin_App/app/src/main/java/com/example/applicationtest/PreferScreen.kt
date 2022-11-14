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
import kotlinx.android.synthetic.main.fragment_prefer_screen.*
import kotlinx.android.synthetic.main.fragment_st_home.*
import java.io.Serializable

class StoreData(
    var storename: String? =null,
    var storeinfo: String? =null,
    var img: Int? =null,
): Serializable {
    fun getData1(): String? {
        return storename
    }
    fun setData1(name: String) {
        this.storename = name
    }
    fun getData2(): String? {
        return storeinfo
    }
    fun setData2(info: String) {
        this.storeinfo = info
    }
    fun getData3(): Int?{
        return img
    }
    fun setData3(type: Int) {
        this.img = img
    }
}

class PreferScreen : Fragment() {

    private  lateinit var storeAdapter: StoreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_prefer_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list: ArrayList<StoreData> = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<StoreData>
        Log.e("FirstFragment", "Data List: ${list}")

        // Fragment에서 전달받은 list를 넘기면서 ListAdapter 생성
        storeAdapter = StoreAdapter(list)
        st_listView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        // RecyclerView.adapter에 지정
        st_listView.adapter = storeAdapter

        // fragment 리스트에 구분선 넣기
        st_listView.addItemDecoration(DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL))

        storeAdapter.setOnItemClickListener(object : StoreAdapter.OnItemClickListener
        {
            override fun onItemClick(v: View, data: StoreData, pos: Int){
                val intent = Intent(getActivity(), StoreDetailActivity::class.java)
                intent.putExtra("data",data)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        })
    }

}