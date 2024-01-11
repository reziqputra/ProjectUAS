package com.example.projectuas.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectuas.navigation.DestinasiNavigasi
import com.example.projectuas.ui.JadwalTopAppBar
import com.example.projectuas.ui.PenyediaViewModel
import com.example.projectuas.ui.add.DestinasiJadwalForm.EntryBody
import kotlinx.coroutines.launch

object EditJadwalDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes ="Edit Jadwal"
    const val jadwalId = "itemId"
    val routeWithArgs = "${EditJadwalDestination.route}/{$jadwalId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditJadwalScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            JadwalTopAppBar(
                title =EditJadwalDestination.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            addUIState = viewModel.JadwalUiState,
            onSiswaValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateJadwal()
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