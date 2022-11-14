package com.example.applicationtest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_search_screen.*

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

        var searchViewTextListener: SearchView.OnQueryTextListener =
            object : SearchView.OnQueryTextListener {

                //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
                override fun onQueryTextSubmit(s: String): Boolean {
                    return false
                }

                //텍스트 입력/수정시에 호출
                override fun onQueryTextChange(s: String): Boolean {
                    return false
                }
            }
    }


}