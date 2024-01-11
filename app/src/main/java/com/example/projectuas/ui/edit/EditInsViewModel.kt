package com.example.projectuas.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectuas.data.InstrukturRepositori
import com.example.projectuas.ui.AddEvent
import com.example.projectuas.ui.AddInsEvent
import com.example.projectuas.ui.AddInsUIState
import com.example.projectuas.ui.AddUIState
import com.example.projectuas.ui.toInstruktur
import com.example.projectuas.ui.toUIStateInstruktur
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditInsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositori: InstrukturRepositori
) : ViewModel() {

    var instrukturUiState by mutableStateOf(AddInsUIState())
        private set

    private val instrukturId: String = checkNotNull(savedStateHandle[EditInsDestination.instrukturId])

    init {
        viewModelScope.launch {
            instrukturUiState =
                repositori.getInstrukturById(instrukturId).filterNotNull().first().toUIStateInstruktur()
        }
    }

    fun updateInsUIState(AddInsEvent: AddInsEvent) {
        instrukturUiState = instrukturUiState.copy(addInsEvent = AddInsEvent)
    }

    suspend fun updateInsInstruktur() {
        repositori.update(instrukturUiState.addInsEvent.toInstruktur())

    }
}

