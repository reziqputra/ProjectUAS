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
import com.example.projectuas.ui.detail.DetailInsDestination
import com.example.projectuas.ui.detail.DetailInsScreen
import com.example.projectuas.ui.detail.DetailScreen
import com.example.projectuas.ui.edit.EditInsDestination
import com.example.projectuas.ui.edit.EditInsScreen
import com.example.projectuas.ui.edit.EditJadwalDestination
import com.example.projectuas.ui.edit.EditJadwalScreen
import com.example.projectuas.ui.home.DestinasiHome
import com.example.projectuas.ui.home.DestinasiInsHome
import com.example.projectuas.ui.home.DestinationAwal
import com.example.projectuas.ui.home.HalamanAwal
import com.example.projectuas.ui.home.HomeInsScreen
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
                navigateToInsForm = { navController.navigate(DestinasiInsHome.route) },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestination.route}/$itemId")
                    println("itemId: $itemId")
                })
        }
        composable(DestinasiInsHome.route) {
            HomeInsScreen(navigateToItemEntry = { navController.navigate(DestinasiInsForm.route) },
                onDetailClick = { itemInsId ->
                    navController.navigate("${DetailInsDestination.route}/$itemInsId")
                    println("itemInsId: $itemInsId")
                },navigateBack = { navController.navigate(DestinasiHome.route) }
                )
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
                    navigateBack = { navController.navigate(DestinasiHome.route) },
                    navigateToEditItem = {
                        navController.navigate("${EditJadwalDestination.route}/$jadwalId")
                        println("jadwalId: $jadwalId")
                    }
                )
            }
        }
        composable(
            route = DetailInsDestination.routeInsWithArgs,
            arguments = listOf(navArgument(DetailInsDestination.instrukturId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val instrukturId = backStackEntry.arguments?.getString(DetailInsDestination.instrukturId)
            instrukturId?.let {
                DetailInsScreen(
                    navigateBack = { navController.navigate(DestinasiInsHome.route) },
                    navigateToEditItem = {
                        navController.navigate("${EditInsDestination.route}/$instrukturId")
                        println("instrukturId: $instrukturId")
                    }
                )
            }
        }
        composable(
            route = EditJadwalDestination.routeWithArgs,
            arguments = listOf(navArgument(EditJadwalDestination.jadwalId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val kontakId = backStackEntry.arguments?.getString(EditJadwalDestination.jadwalId)
            kontakId?.let {
                EditJadwalScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
        composable(
            route = EditInsDestination.routeInsWithArgs,
            arguments = listOf(navArgument(EditInsDestination.instrukturId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val kontakId = backStackEntry.arguments?.getString(EditInsDestination.instrukturId)
            kontakId?.let {
                EditInsScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}