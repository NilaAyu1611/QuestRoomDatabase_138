package com.example.prak8.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.prak8.ui.view.mahasiswa.DestinasiInsert
import com.example.prak8.ui.view.mahasiswa.DetailMhsView
import com.example.prak8.ui.view.mahasiswa.HomeMhsView
import com.example.prak8.ui.view.mahasiswa.InsertMhsView
import com.example.prak8.ui.view.mahasiswa.UpdateMhsView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    )
    {
        composable(
            route = DestinasiHome.route
        ){
            HomeMhsView(
                onDetailClick = {nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                    println(
                        "PengelolaanHalaman: nim = $nim"
                    )
                },
                onAddMhs = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )

        }


    }
}