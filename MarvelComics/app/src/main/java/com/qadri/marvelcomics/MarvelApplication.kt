package com.qadri.marvelcomics

import android.app.Application
import com.qadri.marvelcomics.data.AppContainer
import com.qadri.marvelcomics.data.DefaultAppContainer

class MarvelApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer();
    }
}