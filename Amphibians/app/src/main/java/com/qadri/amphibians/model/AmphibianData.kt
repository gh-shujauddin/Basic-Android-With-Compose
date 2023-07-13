package com.qadri.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibianData (
    @SerialName("name") val name: String,
    @SerialName("type") val type: String,
    @SerialName("description") val description: String,
    @SerialName("img_src") val imgSrc: String
)