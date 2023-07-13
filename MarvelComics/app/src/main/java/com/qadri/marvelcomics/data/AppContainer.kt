package com.qadri.marvelcomics.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.qadri.marvelcomics.network.MarvelApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val marvelRepository: MarvelRepository
}

class DefaultAppContainer: AppContainer {

    private val baseUrl = "https://www.googleapis.com/"

    var json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitServiceForBookId: MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }

    override val marvelRepository: MarvelRepository by lazy {
        NetworkMarvelRepository(retrofitServiceForBookId)
    }
}