package com.example.sfmovieapp.domain.use_case


import com.example.sfmovieapp.data.remote.dto.toMovieList
import com.example.sfmovieapp.domain.model.Movies
import com.example.sfmovieapp.domain.repository.MovieRepository
import com.example.sfmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository){

    suspend operator fun invoke(search: String): Flow<Resource<List<Movies>>> = flow {

        try {
            emit(Resource.Loading())

            val movieList = repository.getMovies(search)
            if (movieList.Response == "True") {
                emit(Resource.Success(movieList.toMovieList()))
            } else {
                emit(Resource.Error(message = "No movie found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message =  e.localizedMessage ?: "Error!"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }

}