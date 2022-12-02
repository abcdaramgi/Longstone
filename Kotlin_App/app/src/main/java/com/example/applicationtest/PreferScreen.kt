package com.example.applicationtest
import android.app.Activity
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
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.fragment_prefer_screen.*
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

    lateinit var storeAdapter: StoreAdapter
    var list: ArrayList<StoreData> = ArrayList()
    val NEW_STORE = 22

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prefer_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("FirstFragment", "Data List: ${list}")

        list = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<StoreData>

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
                val intent = Intent(requireActivity(), StoreDetailActivity::class.java)
                intent.putExtra("data",data)
                intent.putExtra("list",list)
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivityForResult(intent,NEW_STORE)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode : Int, data : Intent?) {
        list = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<StoreData>
        storeAdapter = StoreAdapter(list)
        st_listView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        st_listView.adapter = storeAdapter

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_STORE) {
            Log.d("MDM", "In onActivityResult")
            if (resultCode == Activity.RESULT_OK) {
                val store = data?.getSerializableExtra("new_store") as StoreData//새 레스토랑 받아옴
                list.add(store)
                storeAdapter.notifyDataSetChanged()
            }
        }

        st_listView.addItemDecoration(DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL))

        storeAdapter.setOnItemClickListener(object : StoreAdapter.OnItemClickListener
        {
            override fun onItemClick(v: View, data: StoreData, pos: Int){
                val intent = Intent(requireActivity(), StoreDetailActivity::class.java)
                intent.putExtra("data",data)
                intent.putExtra("list",list)
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivityForResult(intent,NEW_STORE)
            }
        })
    }
}
