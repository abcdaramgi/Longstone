package com.example.applicationtest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PreferScreen : Fragment() {

    companion object {
        fun newInstance(): PreferScreen {
            return PreferScreen()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_prefer_screen, container, false)
    }

}