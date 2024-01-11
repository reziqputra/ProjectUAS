package com.example.projectuas.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectuas.data.InstrukturRepositori
import com.example.projectuas.data.JadwalRepositori
import com.example.projectuas.ui.DetailInsUIState
import com.example.projectuas.ui.DetailUIState
import com.example.projectuas.ui.detail.DetailDestination.jadwalId
import com.example.projectuas.ui.toDetailInstruktur
import com.example.projectuas.ui.toDetailJadwal
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailInsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositori: InstrukturRepositori
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val instrukturId: String = checkNotNull(savedStateHandle[DetailInsDestination.instrukturId])
    val uiInsState: StateFlow<DetailInsUIState> =
        repositori.getInstrukturById(instrukturId)
            .filterNotNull()
            .map {
                DetailInsUIState(addInsEvent = it.toDetailInstruktur())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailInsUIState()
            )





    suspend fun deleteInstruktur() {
        repositori.delete(instrukturId)

    }


}