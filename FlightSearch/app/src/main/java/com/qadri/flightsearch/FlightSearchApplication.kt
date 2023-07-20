package com.qadri.flightsearch

import android.app.Application
import com.qadri.flightsearch.data.AppContainer

class FlightSearchApplication: Application() {
    lateinit var container: AppContainer
}