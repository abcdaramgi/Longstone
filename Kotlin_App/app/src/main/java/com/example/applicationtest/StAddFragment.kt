package com.example.applicationtest

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.applicationtest.DTO.postDTO
import com.example.applicationtest.Transport.PostTask
import com.example.applicationtest.databinding.FragmentStAddBinding

class StAddFragment : Fragment(R.layout.fragment_st_add) {
    private var send_btn: Button? = null
    private var price_edit: EditText? = null
    private var foodName_edit: EditText? = null

    var items: List<postDTO>? = null

    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        send_btn = getView()?.findViewById(R.id.button)
        price_edit = getView()?.findViewById(R.id.editTextPrice)
        foodName_edit = getView()?.findViewById(R.id.editTextFoodName)

        items = ArrayList()

        send_btn?.run {
            setOnClickListener(View.OnClickListener {
                Log.d("post", "post start...")
                post()
            })
        }
    }

    fun post() {
        try {
            val price = price_edit!!.text.toString()
            val foodName = foodName_edit!!.text.toString()

            Log.d("앱에서 보낸값", "$price, $foodName")
            val task = PostTask()
            val result = task.execute(price, foodName).get()
            Log.d("받은값", result)
            Log.d("post", "posting end...")
        } catch (e: Exception) {
        }
    }


    companion object {
        fun newInstance():StAddFragment {
            return StAddFragment()
        }
    }
    //메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    //Fragment를 안고 있는 액티비티에 붙었을 때때
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    // 뷰가 생성되었을 때, 프래그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃과 조각을 서로 연결
        val view = inflater.inflate(R.layout.fragment_st_add, container, false)
        return view
    }
}