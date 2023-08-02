package com.example.sfmovieapp.data.remote

import com.example.sfmovieapp.data.remote.dto.MovieDetailDto
import com.example.sfmovieapp.data.remote.dto.MoviesDto
import com.example.sfmovieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET(".")
    suspend fun getMovies(
        @Query("apikey") apiKey :String = API_KEY,
        @Query("s") search :String
    ) : MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("apikey") apiKey :String = API_KEY,
        @Query("i") imdbId :String
    ) : MovieDetailDto

}