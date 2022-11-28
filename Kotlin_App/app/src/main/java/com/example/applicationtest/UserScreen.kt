package com.example.applicationtest
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_user_screen.*

class UserScreen : Fragment() {

    companion object {
        fun newInstance(): UserScreen {
            return UserScreen()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener({
            val testid = MyApplication.prefs.getString("id", "0")
            val testpw = MyApplication.prefs.getString("pw", "0")
            Log.d("연습용 아이디: ", testid.toString())
            Log.d("연습용 비밀번호: ", testpw.toString())
        })
    }
}