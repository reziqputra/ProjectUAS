package com.example.projectuas.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectuas.data.JadwalRepositori
import com.example.projectuas.ui.AddEvent
import com.example.projectuas.ui.AddUIState
import com.example.projectuas.ui.toJadwal
import com.example.projectuas.ui.toUIStateJadwal
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: JadwalRepositori
) : ViewModel() {

    var JadwalUiState by mutableStateOf(AddUIState())
        private set

    private val jadwalId: String = checkNotNull(savedStateHandle[EditJadwalDestination.jadwalId])

    init {
        viewModelScope.launch {
            JadwalUiState =
                repository.getJadwalById(jadwalId)
                    .filterNotNull()
                    .first()
                    .toUIStateJadwal()
        }
    }

    fun updateUIState(addEvent: AddEvent) {
        JadwalUiState = JadwalUiState.copy(addEvent = addEvent)
    }

    suspend fun updateJadwal() {
        repository.update(JadwalUiState.addEvent.toJadwal())

    }
}