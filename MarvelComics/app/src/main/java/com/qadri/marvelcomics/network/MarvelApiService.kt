package com.qadri.marvelcomics.network

import com.qadri.marvelcomics.model.ComicID
import com.qadri.marvelcomics.model.Book
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApiService {

    @GET("books/v1/volumes?q=ncert")
    suspend fun getMarvelBookId(): ComicID

    @GET("books/v1/volumes/{id}")
    suspend fun getMarvelThumbnail(@Path("id") id: String): Book
}