package com.qadri.marvelcomics.data

import com.qadri.marvelcomics.model.ComicID
import com.qadri.marvelcomics.model.Book
import com.qadri.marvelcomics.network.MarvelApiService

interface MarvelRepository {
    suspend fun getMarvelBookId(): ComicID
    suspend fun getMarvelBookThumbnail(id: String): Book
}

class NetworkMarvelRepository(private val marvelApiService: MarvelApiService) : MarvelRepository {
    override suspend fun getMarvelBookId(): ComicID = marvelApiService.getMarvelBookId()
    override suspend fun getMarvelBookThumbnail(id: String): Book {
       return marvelApiService.getMarvelThumbnail(id = id)

    }

}