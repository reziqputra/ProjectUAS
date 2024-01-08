package com.example.projectuas.ui.home

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
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectuas.model.Jadwal
import com.example.projectuas.navigation.DestinasiNavigasi
import com.example.projectuas.ui.JadwalTopAppBar
import com.example.projectuas.ui.PenyediaViewModel

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Jadwal"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            JadwalTopAppBar(
                title = "Jadwal Kursus Mobil",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
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
        val uiStateJadwal by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemJadwal = uiStateJadwal.listJadwal,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onJadwalClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemJadwal: List<Jadwal>,
    modifier: Modifier = Modifier,
    onJadwalClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemJadwal.isEmpty()) {
            Text(
                text = "Tidak ada data Jadwal",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListJadwal(
                itemJadwal = itemJadwal,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onJadwalClick(it.id) }
            )
        }
    }
}

@Composable
fun ListJadwal(
    itemJadwal: List<Jadwal>,
    modifier: Modifier = Modifier,
    onItemClick: (Jadwal) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemJadwal, key = { it.id }) { jadwal ->
            DataJadwal(
                jadwal = jadwal,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(jadwal) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataJadwal(
    jadwal: Jadwal,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = jadwal.tanggal,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = jadwal.waktu,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = jadwal.nama_Instruktur,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = jadwal.nama_Kursus,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}