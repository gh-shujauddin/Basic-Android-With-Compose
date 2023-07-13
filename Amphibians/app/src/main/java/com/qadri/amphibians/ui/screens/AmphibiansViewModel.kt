package com.qadri.amphibians.ui.screens

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.qadri.amphibians.AmphibianApplication
import com.qadri.amphibians.data.AmphibianDataRepository
import com.qadri.amphibians.data.AppContainer
import com.qadri.amphibians.data.DefaultAppContainer
import com.qadri.amphibians.data.NetworkAmphibianDataRepository
import com.qadri.amphibians.model.AmphibianData
import com.qadri.amphibians.network.AmphibianApiService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class AmphibiansViewModel(private val amphibianDataRepository: AmphibianDataRepository) :
    ViewModel() {
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibiansData()
    }

    fun getAmphibiansData() {
        viewModelScope.launch {
            amphibianUiState = AmphibianUiState.Loading
            amphibianUiState = try {
                AmphibianUiState.Success(
                    amphibianDataRepository.getAmphibianData()
                )
            } catch (e: IOException) {
                AmphibianUiState.Error
            } catch (e: HttpException) {
                AmphibianUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application: AmphibianApplication =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibianApplication)
                val amphibianDataRepository = application.container.amphibianDataRepository
                AmphibiansViewModel(amphibianDataRepository = amphibianDataRepository)
            }
        }
    }
}

sealed interface AmphibianUiState {
    data class Success(val amphibians: List<AmphibianData>) : AmphibianUiState
    object Loading : AmphibianUiState
    object Error : AmphibianUiState
}