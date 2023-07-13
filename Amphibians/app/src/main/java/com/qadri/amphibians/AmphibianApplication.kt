package com.qadri.amphibians

import android.app.Application
import com.qadri.amphibians.data.AppContainer
import com.qadri.amphibians.data.DefaultAppContainer

class AmphibianApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}