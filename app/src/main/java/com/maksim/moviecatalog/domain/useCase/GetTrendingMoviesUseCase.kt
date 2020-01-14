package com.maksim.moviecatalog.domain.useCase

import androidx.lifecycle.LiveData
import com.maksim.moviecatalog.domain.repository.MovieRepository
import com.maksim.moviecatalog.domain.repository.Resource

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class GetTrendingMoviesUseCase(private val movieRepository: MovieRepository) {
  fun execute(): LiveData<Resource<Boolean>> {
    return movieRepository.getTrendingMovies()
  }
}