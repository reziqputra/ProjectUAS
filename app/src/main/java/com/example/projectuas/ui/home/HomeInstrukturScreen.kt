package com.example.projectuas.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectuas.model.Instruktur
import com.example.projectuas.navigation.DestinasiNavigasi
import com.example.projectuas.ui.JadwalTopAppBar
import com.example.projectuas.ui.PenyediaViewModel

object DestinasiInsHome : DestinasiNavigasi {
    override val route = "home2"
    override val titleRes = "Instruktur"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeInsScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            JadwalTopAppBar(
                title = "Instruktur Kursus Mobil",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {

                FloatingActionButton(
                    onClick = navigateToItemEntry,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = ""
                    )
                }

        },
    ) { innerPadding ->
        val uiStateInstruktur by viewModel.homeInsUIState.collectAsState()
        BodyHome(
            itemInstruktur = uiStateInstruktur.listInstruktur,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFABB28D)),
            onInstrukturClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemInstruktur: List<Instruktur>,
    modifier: Modifier = Modifier,
    onInstrukturClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemInstruktur.isEmpty()) {
            Text(
                text = "Tidak ada data Instruktur",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            Spacer(modifier = Modifier.padding(8.dp))
            ListInstruktur(
                itemInstruktur = itemInstruktur,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onInstrukturClick(it.id) }
            )
        }
    }
}

@Composable
fun ListInstruktur(
    itemInstruktur: List<Instruktur>,
    modifier: Modifier = Modifier,
    onItemClick: (Instruktur) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemInstruktur, key = { it.id }) { instruktur ->
            DataInstruktur(
                instruktur = instruktur,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(instruktur) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataInstruktur(
    instruktur: Instruktur,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFCDCAAF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = instruktur.nama,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = instruktur.telpon,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}