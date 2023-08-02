package com.example.sfmovieapp.domain.repository

import com.example.sfmovieapp.data.remote.dto.MovieDetailDto
import com.example.sfmovieapp.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies(search: String): MoviesDto

    suspend fun getMovieDetail(imdbId: String): MovieDetailDto

}