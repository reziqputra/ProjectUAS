package com.example.projectuas.ui.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectuas.navigation.DestinasiNavigasi
import com.example.projectuas.ui.AddEvent
import com.example.projectuas.ui.AddUIState
import com.example.projectuas.ui.JadwalTopAppBar
import com.example.projectuas.ui.PenyediaViewModel
import kotlinx.coroutines.launch


object DestinasiJadwalForm : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Entry Jadwal"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddJadwalScreen(
        navigateBack: () -> Unit,
        modifier: Modifier = Modifier,
        addViewModel: AddViewModel = viewModel(factory = PenyediaViewModel.Factory),

        ) {
        val coroutineScope = rememberCoroutineScope()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                JadwalTopAppBar(
                    title = titleRes,
                    canNavigateBack = true,
                    scrollBehavior = scrollBehavior,
                    navigateUp = navigateBack
                )
            }
        ) { innerPadding ->

            EntryBody(
                addUIState = addViewModel.addUIState,
                onSiswaValueChange = addViewModel::updateAddUIState,
                onSaveClick = {
                    coroutineScope.launch {
                        addViewModel.addJadwal()
                        navigateBack()
                    }
                },
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .background(Color(0xFFABB28D)),
                )
        }
    }

    @Composable
    fun EntryBody(
        addUIState: AddUIState,
        onSiswaValueChange: (AddEvent) -> Unit,
        onSaveClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .padding(12.dp)
            ) {
            FormInput(
                addEvent = addUIState.addEvent,
                onValueChange = onSiswaValueChange,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onSaveClick,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FormInput(
        addEvent: AddEvent,
        modifier: Modifier = Modifier,
        onValueChange: (AddEvent) -> Unit = {},
        enabled: Boolean = true
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = addEvent.tanggal,
                onValueChange = { onValueChange(addEvent.copy(tanggal = it)) },
                label = { Text("tanggal") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                enabled = enabled,
                singleLine = true,

                )
            OutlinedTextField(
                value = addEvent.waktu,
                onValueChange = { onValueChange(addEvent.copy(waktu = it)) },
                label = { Text("Waktu") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = addEvent.nm_instruktur,
                onValueChange = { onValueChange(addEvent.copy(nm_instruktur = it)) },
                label = { Text("Instruktur") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = addEvent.nm_kursus,
                onValueChange = { onValueChange(addEvent.copy(nm_kursus = it)) },
                label = { Text("Nama Kursus") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )

        }
    }

}