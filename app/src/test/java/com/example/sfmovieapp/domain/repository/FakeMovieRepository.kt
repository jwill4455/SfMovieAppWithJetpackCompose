package com.example.sfmovieapp.domain.repository

import com.example.sfmovieapp.data.remote.dto.MovieDetailDto
import com.example.sfmovieapp.data.remote.dto.MoviesDto
import com.example.sfmovieapp.data.remote.dto.Rating
import com.example.sfmovieapp.data.remote.dto.Search
import com.example.sfmovieapp.data.repository.MovieRepositoryImpl

class FakeMovieRepository: MovieRepository {

    private val fakeMovies: MoviesDto = createFakeMoviesDto()
    private val fakeMovieDetail: MovieDetailDto = createFakeMovieDetailDto()


    override suspend fun getMovies(search: String): MoviesDto {
        return fakeMovies
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return fakeMovieDetail
    }

    private fun createFakeMoviesDto(): MoviesDto {
        // Create a list of fake Search objects
        val fakeSearchList = listOf(
            Search(
                Poster = "https://example.com/poster1.jpg",
                Title = "Movie 1",
                Year = "2023",
                imdbID = "tt001",
                Type = "Movie"
            ),
            Search(
                Poster = "https://example.com/poster2.jpg",
                Title = "Movie 2",
                Year = "2022",
                imdbID = "tt002",
                Type = "Movie"
            ),
        )

        return MoviesDto(
            Response = "True",
            Search = fakeSearchList,
            totalResults = fakeSearchList.size.toString()
        )
    }

    private fun createFakeMovieDetailDto(): MovieDetailDto {
        val fakeRatings = listOf(
            Rating("Internet Movie Database", "8.0/10"),
            Rating("Rotten Tomatoes", "90%")
        )

        return MovieDetailDto(
            Actors = "Actor 1, Actor 2",
            Awards = "Best Movie Award",
            Country = "USA",
            Director = "Director Name",
            Genre = "Action, Adventure",
            Language = "English",
            Poster = "https://example.com/poster.jpg",
            Rated = "PG-13",
            Released = "2023-07-31",
            Title = "Sample Movie",
            Type = "Movie",
            Year = "2023",
            imdbID = "tt001",
            imdbRating = "8.0",
            imdbVotes = "12345",
            BoxOffice = "N/A",
            DVD = "N/A",
            Metascore = "N/A",
            Plot = "Sample plot",
            Production = "N/A",
            Response = "True",
            Runtime = "N/A",
            Website = "N/A",
            Writer = "Writer Name",
            Ratings = fakeRatings
        )
    }
}