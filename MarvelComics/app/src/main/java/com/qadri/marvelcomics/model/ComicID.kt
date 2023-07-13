package com.qadri.marvelcomics.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicID(
    val items: List<Items?>
)

@Serializable
data class Items(
    val id: String
)