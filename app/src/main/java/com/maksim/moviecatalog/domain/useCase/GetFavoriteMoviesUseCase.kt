package com.maksim.moviecatalog.domain.useCase

import androidx.lifecycle.LiveData
import com.maksim.moviecatalog.domain.model.Movie
import com.maksim.moviecatalog.domain.repository.MovieRepository

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class GetFavoriteMoviesUseCase(private val movieRepository: MovieRepository) {
  fun execute(): LiveData<List<Movie>> {
    return movieRepository.getFavoriteMovies()
  }
}