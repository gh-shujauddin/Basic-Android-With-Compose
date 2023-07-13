package com.qadri.marvelcomics.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.qadri.marvelcomics.MarvelApplication
import com.qadri.marvelcomics.data.MarvelRepository
import com.qadri.marvelcomics.model.ComicID
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MarvelComicsUiState {
    data class Success(val book: MutableList<String>) : MarvelComicsUiState
    object Error : MarvelComicsUiState
    object Loading : MarvelComicsUiState

}

class MarvelViewModel(private val marvelRepository: MarvelRepository) : ViewModel() {
    var marvelUiState: MarvelComicsUiState by mutableStateOf(MarvelComicsUiState.Loading)
        private set

    init {
        getMarvelComicsData()
    }

    fun getMarvelComicsData() {
        viewModelScope.launch {
            marvelUiState = MarvelComicsUiState.Loading
            marvelUiState = try {
                val bookId = marvelRepository.getMarvelBookId().items

                val bookThumbnail: MutableList<String> = mutableListOf()
                for (id in bookId) {
                    val comicId = id?.let { marvelRepository.getMarvelBookThumbnail(id = it.id).volumeInfo.imageLinks }
                    if (comicId != null) {
                        bookThumbnail.add(comicId.thumbnail)
                    }
                }
                MarvelComicsUiState.Success(bookThumbnail)

            } catch (e: IOException) {
                MarvelComicsUiState.Error
            } catch (e: HttpException) {
                MarvelComicsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application: MarvelApplication = (this[APPLICATION_KEY] as MarvelApplication)
                val marvelRepository = application.container.marvelRepository
                MarvelViewModel(marvelRepository = marvelRepository)

            }

        }
    }
}