package com.example.projectuas.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectuas.data.JadwalRepositori
import com.example.projectuas.ui.DetailUIState
import com.example.projectuas.ui.toDetailJadwal
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: JadwalRepositori
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val jadwalId: String = checkNotNull(savedStateHandle[DetailDestination.jadwalId])

    val uiState: StateFlow<DetailUIState> =
        repository.getJadwalById(jadwalId)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailJadwal())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deleteJadwal() {
        repository.delete(jadwalId)

    }


}