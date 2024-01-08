package com.example.projectuas.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectuas.ui.add.DestinasiJadwalForm
import com.example.projectuas.ui.add.DestinasiJadwalForm.AddJadwalScreen
import com.example.projectuas.ui.detail.DetailDestination
import com.example.projectuas.ui.detail.DetailScreen
import com.example.projectuas.ui.home.DestinasiHome
import com.example.projectuas.ui.home.HomeScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ){
        composable(DestinasiHome.route){
            HomeScreen(navigateToItemEntry = {navController.navigate(DestinasiJadwalForm.route)},
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestination.route}/$itemId")
                    println("itemId: $itemId")
                })
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
                        println("jadwalId: $jadwalId")
                    }
                )
            }
        }
    }
}