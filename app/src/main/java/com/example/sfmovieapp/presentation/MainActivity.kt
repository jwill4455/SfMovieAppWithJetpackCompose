package com.example.sfmovieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sfmovieapp.presentation.movie_detail.views.MovieDetailScreen
import com.example.sfmovieapp.presentation.movies.views.MoviesScreen
import com.example.sfmovieapp.presentation.theme.SfMovieAppTheme
import com.example.sfmovieapp.util.Constants.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SfMovieAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MoviesScreen.route
                    ) {
                        composable(
                            route = Screen.MoviesScreen.route
                        ) {
                            MoviesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.MovieDetailScreen.route+"/{${IMDB_ID}}"
                        ) {
                            MovieDetailScreen()
                        }
                    }
                }

            }
        }
    }
}
