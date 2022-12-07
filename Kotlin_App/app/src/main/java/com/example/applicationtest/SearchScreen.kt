package com.example.applicationtest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.applicationtest.Transport.RegisterTask
import com.example.applicationtest.Transport.SearchTask
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_search_screen.*
import java.lang.reflect.Type

class SearchScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
            override fun onQueryTextSubmit(s: String): Boolean {

                try {
                    val content = s
                    val task = SearchTask()
                    val result = task.execute(content).get()

                    val gson = Gson()
                    val listType: Type = object : TypeToken<ArrayList<StoreData2?>?>() {}.type
                    val yourClassList: List<StoreData2> = Gson().fromJson(result, listType)

                    Log.d("받은값", result)
                    Log.d("Register", "register end...")
                    Log.d("리스트변환했냐", yourClassList.get(1).name.toString())
                } catch (e: Exception) {
                }

                return false
            }

            //텍스트 입력/수정시에 호출
            override fun onQueryTextChange(s: String): Boolean {
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

}