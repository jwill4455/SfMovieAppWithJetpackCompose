package com.example.sfmovieapp.data.repository

import com.example.sfmovieapp.data.remote.MovieAPI
import com.example.sfmovieapp.data.remote.dto.MovieDetailDto
import com.example.sfmovieapp.data.remote.dto.MoviesDto
import com.example.sfmovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api : MovieAPI) : MovieRepository {

    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(search = search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId = imdbId)
    }

}