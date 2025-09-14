package com.example.gallery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gallery.domain.model.Photos
import com.example.gallery.presentation.details.Details
import com.example.gallery.presentation.results.ImageResults
import com.google.gson.Gson


@Composable
fun GalleryApp() {

    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.Result.route
    ) {

        composable(route = Screens.Result.route) {
            ImageResults(navController = navController)
        }

        composable(
            route = Screens.Details.route + "/{photo}",
            arguments = listOf(navArgument("photo") { type = NavType.StringType })
        ) { backStackEntry ->

            val json = backStackEntry.arguments?.getString("photo")
            val photo = json?.let { Gson().fromJson(it, Photos::class.java) }

            photo?.let {
                Details(photo = it, onBackClick = { navController.popBackStack() })
            }
        }

    }
}