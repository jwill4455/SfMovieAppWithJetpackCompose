package com.example.sfmovieapp.presentation.movies

import com.example.sfmovieapp.domain.model.Movies

data class MoviesState(
    val isLoading : Boolean = false,
    val movies : List<Movies> = emptyList(),
    val error : String = "",
    val search : String = "Gora",
)
