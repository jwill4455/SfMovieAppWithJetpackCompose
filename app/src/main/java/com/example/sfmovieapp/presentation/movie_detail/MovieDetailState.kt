package com.example.sfmovieapp.presentation.movie_detail

import com.example.sfmovieapp.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading : Boolean = false,
    val movie : MovieDetail? = null,
    val error : String = ""
)