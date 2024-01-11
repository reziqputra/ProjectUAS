package com.example.projectuas.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projectuas.application
import com.example.projectuas.data.InstrukturRepositori
import com.example.projectuas.ui.add.AddViewModel
import com.example.projectuas.ui.detail.DetailInsViewModel
import com.example.projectuas.ui.detail.DetailViewModel
import com.example.projectuas.ui.edit.EditInsViewModel
import com.example.projectuas.ui.edit.EditViewModel
import com.example.projectuas.ui.home.HomeViewModel

fun CreationExtras.aplikasJadwal(): application =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as application)

fun CreationExtras.aplikasInstruktur(): application =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as application)

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            AddViewModel(aplikasJadwal().container.jadwalRepositori,aplikasInstruktur().container.instrukturRepositori)
        }

        initializer {
            HomeViewModel(aplikasJadwal().container.jadwalRepositori,aplikasInstruktur().container.instrukturRepositori )
        }

        initializer {
            DetailViewModel(createSavedStateHandle(),aplikasJadwal().container.jadwalRepositori)
        }
        initializer { DetailInsViewModel(createSavedStateHandle(),aplikasInstruktur().container.instrukturRepositori) }
        initializer {
            EditViewModel(createSavedStateHandle(),aplikasJadwal().container.jadwalRepositori)
        }
        initializer {
            EditInsViewModel(createSavedStateHandle(),aplikasInstruktur().container.instrukturRepositori)
        }
    }
}