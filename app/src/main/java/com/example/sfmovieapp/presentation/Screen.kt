package com.example.sfmovieapp.presentation

sealed class Screen (val route:String) {
    object MoviesScreen : Screen("movies_screen")
    object MovieDetailScreen : Screen("movie_detail_screen")
}
