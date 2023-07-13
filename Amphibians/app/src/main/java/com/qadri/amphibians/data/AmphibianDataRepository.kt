package com.qadri.amphibians.data

import com.qadri.amphibians.model.AmphibianData
import com.qadri.amphibians.network.AmphibianApiService

interface AmphibianDataRepository {
    suspend fun getAmphibianData(): List<AmphibianData>
}

class NetworkAmphibianDataRepository(
    private val amphibianApiService: AmphibianApiService
): AmphibianDataRepository {
    override suspend fun getAmphibianData(): List<AmphibianData> = amphibianApiService.getData()
}