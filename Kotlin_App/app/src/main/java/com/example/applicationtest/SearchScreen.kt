package com.example.applicationtest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationtest.DTO.FoodData
import com.example.applicationtest.DTO.OnSalePostDTO
import com.example.applicationtest.DTO.StoreDTO
import com.example.applicationtest.Transport.SearchTask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_prefer_screen.*
import kotlinx.android.synthetic.main.fragment_search_screen.*
import okhttp3.internal.platform.android.AndroidLogHandler.getFilter
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type

class SearchScreen : Fragment() {

    lateinit var storeAdapter1: StoreAdapter1
    var list: ArrayList<StoreData> = ArrayList()
    val NEW_STORE = 22

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_bar.setOnClickListener(View.OnClickListener { search_bar.setIconified(false) })

        Log.e("FirstFragment", "Data List: ${list}")

        list = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<StoreData>

        // Fragment에서 전달받은 list를 넘기면서 ListAdapter 생성
        storeAdapter1 = StoreAdapter1(list)
        search_re.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        // RecyclerView.adapter에 지정
        search_re.adapter = storeAdapter1

        // fragment 리스트에 구분선 넣기
        search_re.addItemDecoration(DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL))

        storeAdapter1.setOnItemClickListener(object : StoreAdapter1.OnItemClickListener
        {
            override fun onItemClick(v: View, data: StoreData, pos: Int){
                val intent = Intent(requireActivity(), StoreDetailActivity::class.java)
                intent.putExtra("data",data)
                intent.putExtra("list",list)
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivityForResult(intent,NEW_STORE)
            }
        })

            search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
                override fun onQueryTextSubmit(s: String): Boolean {
                    try {
                        list!!.clear();
                        val content = s
                        val task = SearchTask()
                        val result = task.execute(content).get()

                        Log.d("받은값", result)


                        if(result != null){
                            val `object` = JSONObject(result)
                            val array = `object`.get("store") as JSONArray

                            for(i: Int in 0..array.length()-1){
                                var row = array.getJSONObject(i)

                                list!!.add(
                                    StoreData(
                                    row.getString("storeName"),
                                    row.getString("pdName"),
                                    row.getString("imgUrl")
                                )
                                );
                            }
                        }
                        else{
                            Log.d("Onsale", "Onsale fail...")
                        }



                    } catch (e: Exception) {
                    }
                    return false
                }

                //텍스트 입력/수정시에 호출
                override fun onQueryTextChange(s: String): Boolean {
                    storeAdapter1.getFilter().filter(s)
                    Log.d("나오냐?", s)
                    return true
                }
            })

//        var searchViewTextListener: SearchView.OnQueryTextListener =
//            object : SearchView.OnQueryTextListener {
//
//                //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
//                override fun onQueryTextSubmit(s: String): Boolean {
//                    print(s)
//                    Log.d("나오냐?", s)
//
//                    return false
//                }
//
//                //텍스트 입력/수정시에 호출
//                override fun onQueryTextChange(s: String): Boolean {
//                    Log.d("나오냐?", s)
//                    return true
//                }
//            }
    }

    override fun onActivityResult(requestCode: Int, resultCode : Int, data : Intent?) {
        list = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<StoreData>
        storeAdapter1 = StoreAdapter1(list)
        search_re.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        search_re.adapter = storeAdapter1

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_STORE) {
            Log.d("MDM", "In onActivityResult")
            if (resultCode == Activity.RESULT_OK) {
                val store = data?.getSerializableExtra("new_store") as StoreData//새 레스토랑 받아옴
                list.add(store)
                storeAdapter1.notifyDataSetChanged()
            }
        }

        search_re.addItemDecoration(DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL))

        storeAdapter1.setOnItemClickListener(object : StoreAdapter1.OnItemClickListener
        {
            override fun onItemClick(v: View, data: StoreData, pos: Int){
                val intent = Intent(requireActivity(), StoreDetailActivity::class.java)
                //intent.putExtra("data",data)
                intent.putExtra("list",list)
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivityForResult(intent,NEW_STORE)
            }
        })
    }

}