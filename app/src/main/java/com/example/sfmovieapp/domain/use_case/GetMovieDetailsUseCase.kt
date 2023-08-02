package com.example.sfmovieapp.domain.use_case

import com.example.sfmovieapp.data.remote.dto.toMovieDetail
import com.example.sfmovieapp.domain.model.MovieDetail
import com.example.sfmovieapp.domain.repository.MovieRepository
import com.example.sfmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository){

      suspend operator fun invoke(imdbId: String): Flow<Resource<MovieDetail>> = flow {

          try {
              emit(Resource.Loading())

              val movieDetail = repository.getMovieDetail(imdbId = imdbId).toMovieDetail()
              emit(Resource.Success(movieDetail))
          } catch (e: HttpException) {
              emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
          } catch (e: IOException) {
              emit(Resource.Error(message = "Could not reach internet"))
          }

      }

}