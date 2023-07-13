package com.qadri.amphibians.network

import com.qadri.amphibians.model.AmphibianData
import retrofit2.http.GET

interface AmphibianApiService {

    @GET("amphibians")
    suspend fun getData(): List<AmphibianData>
}