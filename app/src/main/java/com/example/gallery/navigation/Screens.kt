package com.example.gallery.navigation

sealed class Screens(val route: String) {

    object Result : Screens("result_screen")
    object Details : Screens("details_screen")

}