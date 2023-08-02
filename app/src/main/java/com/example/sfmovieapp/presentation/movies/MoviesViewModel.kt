package com.example.sfmovieapp.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sfmovieapp.domain.use_case.GetMoviesUseCase
import com.example.sfmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MoviesState())
    val state : State<MoviesState> = _state

    private var moviesJob : Job? = null

    init {
        getMovies(_state.value.search)
    }

    private fun getMovies(search: String) {

        moviesJob?.cancel()

        moviesJob = viewModelScope.launch {
            getMoviesUseCase(search).onEach {
                 when(it) {
                     is Resource.Success -> {
                         _state.value = MoviesState(movies = it.data ?: emptyList())
                     }

                     is Resource.Error -> {
                         _state.value = MoviesState(error = it.message ?: "Error!")
                     }

                     is Resource.Loading -> {
                         _state.value = MoviesState(isLoading = true)
                     }
                 }
            }.collect()
        }

    }

    fun onEvent(event : MoviesEvent) {
        when(event) {
            is MoviesEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }



}