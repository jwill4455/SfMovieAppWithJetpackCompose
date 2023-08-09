package com.example.sfmovieapp.domain.use_case

import com.example.sfmovieapp.data.remote.dto.MoviesDto
import com.example.sfmovieapp.domain.model.Movies
import com.example.sfmovieapp.domain.repository.FakeMovieRepository
import com.example.sfmovieapp.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseTest {

    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var fakeMovieRepository: FakeMovieRepository

    @Before
    fun setup() {
        fakeMovieRepository = mockk()
        getMoviesUseCase = GetMoviesUseCase(fakeMovieRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return error when repository returns an empty list`() = runTest {

        coEvery { fakeMovieRepository.getMovies(any()) } returns MoviesDto(
            Response = "True",
            Search = emptyList(),
            totalResults = "0"
        )


        val result = mutableListOf<Resource<List<Movies>>>()
        getMoviesUseCase("search query").collect {
            result.add(it)
        }


        val loading = Resource.Loading<List<Movies>>()
        val error = Resource.Error<List<Movies>>("No movie found")

        assertEquals(loading.data, result[0].data)
        assertEquals(null, result[0].message)
        assertTrue(result[0] is Resource.Loading)

        assertEquals(error.data, result[1].data)
        assertEquals("No movie found", result[1].message)
        assertTrue(result[1] is Resource.Error)
    }

}