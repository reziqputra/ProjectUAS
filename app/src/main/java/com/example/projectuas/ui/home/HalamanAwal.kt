package com.example.projectuas.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectuas.R
import com.example.projectuas.navigation.DestinasiNavigasi


object DestinationAwal : DestinasiNavigasi {
    override val route = "item_first"
    override val titleRes = "First"
}

@Composable
fun HalamanAwal(
    onNextButtonClicked: () -> Unit
) {
    val image = painterResource(id = R.drawable.mobillll)
    Image(
        painter = painterResource(id = R.drawable.backroundqq),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFCDCAAF),
            ),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 50.dp, horizontal = 30.dp)
                .align(Alignment.CenterHorizontally)
                .shadow(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Spacer(modifier = Modifier.padding(15.dp))
                Text(
                    text = "Jadwal kursus",
                    color = Color.DarkGray,
                    fontSize = 40.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)  // Sesuaikan ukuran gambar di sini
                        .clip(shape = MaterialTheme.shapes.medium)
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.padding(15.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNextButtonClicked
            )
            {
                Text(stringResource(R.string.next))
            }
        }
    }
}
