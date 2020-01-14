package com.maksim.moviecatalog.domain.useCase

import androidx.lifecycle.LiveData
import com.maksim.moviecatalog.domain.model.Movie
import com.maksim.moviecatalog.domain.repository.MovieRepository

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class GetAllMoviesLiveDataUseCase(private val movieRepository: MovieRepository) {

  fun execute(): LiveData<List<Movie>> {
    return movieRepository.getAllMovies()
  }
}