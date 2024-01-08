package com.example.projectuas.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectuas.data.JadwalRepositori
import com.example.projectuas.model.Jadwal
import com.example.projectuas.ui.HomeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class JadwalUIState {
    data class Success(val jadwal: Flow<List<Jadwal>>) : JadwalUIState()
    object Error : JadwalUIState()
    object Loading : JadwalUIState()
}

class HomeViewModel(private val jadwalRepositori: JadwalRepositori) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIState: StateFlow<HomeUIState> = jadwalRepositori.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listJadwal = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()

        )

}