package com.example.projectuas

import android.app.Application
import com.example.projectuas.data.AppContainer
import com.example.projectuas.data.appsContainer

class application : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = appsContainer()
    }
}

