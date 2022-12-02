package com.example.applicationtest

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.applicationtest.Singleton.PreferenceUtil

class MyApplication : Application()
{
    companion object
    {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate()
    {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}