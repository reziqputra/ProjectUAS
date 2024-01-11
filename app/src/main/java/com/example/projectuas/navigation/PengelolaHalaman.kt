package com.example.projectuas.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectuas.ui.add.AddInstrukturScreen
import com.example.projectuas.ui.add.DestinasiInsForm
import com.example.projectuas.ui.add.DestinasiJadwalForm
import com.example.projectuas.ui.add.DestinasiJadwalForm.AddJadwalScreen
import com.example.projectuas.ui.detail.DetailDestination
import com.example.projectuas.ui.detail.DetailScreen
import com.example.projectuas.ui.home.DestinasiHome
import com.example.projectuas.ui.home.DestinationAwal
import com.example.projectuas.ui.home.HalamanAwal
import com.example.projectuas.ui.home.HomeScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = DestinationAwal.route,
        modifier = Modifier
    ) {

        composable(DestinationAwal.route){
            HalamanAwal (onNextButtonClicked = {navController.navigate(DestinasiHome.route)})
        }
        composable(DestinasiHome.route) {
            HomeScreen(navigateToItemEntry = { navController.navigate(DestinasiJadwalForm.route) },
                navigateToInsForm = { navController.navigate(DestinasiInsForm.route) },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestination.route}/$itemId")
                    println("itemId: $itemId")
                })
        }
        composable(DestinasiInsForm.route){
            AddInstrukturScreen(navigateBack = { navController.popBackStack() })
        }

        composable(DestinasiJadwalForm.route) {
            AddJadwalScreen(navigateBack = {
                navController.popBackStack()
            })

        }
        composable(
            route = DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.jadwalId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val jadwalId = backStackEntry.arguments?.getString(DetailDestination.jadwalId)
            jadwalId?.let {
                DetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = {
                        //navController.navigate("${EditDestination.route}/$jadwalId")
                        //println("jadwalId: $jadwalId")
                    }
                )
            }
        }
    }
}