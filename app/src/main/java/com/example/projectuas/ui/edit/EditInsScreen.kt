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
import com.example.projectuas.ui.add.EntryInsBody
import kotlinx.coroutines.launch

object EditInsDestination : DestinasiNavigasi {
    override val route = "item_edit2"
    override val titleRes ="Edit Instruktur"
    const val instrukturId = "itemInsId"
    val routeInsWithArgs = "${EditInsDestination.route}/{$instrukturId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInsScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditInsViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            JadwalTopAppBar(
                title =EditInsDestination.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryInsBody(
            addInsUIState = viewModel.instrukturUiState,
            onSiswaValueChange = viewModel::updateInsUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateInsInstruktur()
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