package com.qadri.marvelcomics.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val imageLinks: ImageLinks
)

@Serializable
data class ImageLinks(
    val thumbnail: String
)