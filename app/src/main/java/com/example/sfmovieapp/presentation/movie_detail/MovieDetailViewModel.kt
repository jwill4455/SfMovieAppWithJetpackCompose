package com.example.sfmovieapp.presentation.movie_detail


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sfmovieapp.domain.use_case.GetMovieDetailsUseCase
import com.example.sfmovieapp.util.Constants.IMDB_ID
import com.example.sfmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state : State<MovieDetailState> = _state

    init {
        savedStateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId: String) {
        viewModelScope.launch {
            getMovieDetailsUseCase(imdbId= imdbId).onEach {
                when (it) {
                    is Resource.Success -> {
                        _state.value = MovieDetailState(movie = it.data)
                    }
                    is Resource.Error -> {
                        _state.value = MovieDetailState(error= it.message ?: "Error!")
                    }
                    is Resource.Loading -> {
                        _state.value = MovieDetailState(isLoading = true)
                    }
                }
            }.collect()
        }

    }

}