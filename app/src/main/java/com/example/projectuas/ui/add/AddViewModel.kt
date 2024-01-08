package com.example.projectuas.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.projectuas.data.InstrukturRepositori
import com.example.projectuas.data.JadwalRepositori
import com.example.projectuas.ui.AddEvent
import com.example.projectuas.ui.AddInsEvent
import com.example.projectuas.ui.AddInsUIState
import com.example.projectuas.ui.AddUIState
import com.example.projectuas.ui.toInstruktur
import com.example.projectuas.ui.toJadwal

class AddViewModel(private val jadwalRepositori: JadwalRepositori,private val instrukturRepositori: InstrukturRepositori ) : ViewModel() {

    var addUIState by mutableStateOf(AddUIState())
        private set
    fun updateAddUIState(addEvent: AddEvent) {
        addUIState = AddUIState(addEvent = addEvent)
    }

    var addInsUIState by mutableStateOf(AddInsUIState())
        private  set
    fun updateAddInsUIState(addInsEvent: AddInsEvent) {
        addInsUIState = AddInsUIState(addInsEvent = addInsEvent)
    }


    suspend fun addJadwal() {
        jadwalRepositori.save(addUIState.addEvent.toJadwal())
    }

    suspend fun AddInstruktur(){
        instrukturRepositori.save(addInsUIState.addInsEvent.toInstruktur())
    }



}