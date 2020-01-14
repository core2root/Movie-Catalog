package com.maksim.moviecatalog.domain.useCase

import com.maksim.moviecatalog.domain.repository.MovieRepository

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class UpdateMovieFavoriteStatusUseCase(private val movieRepository: MovieRepository) {

  fun execute( movieId: Int) {
    movieRepository.updateMovieFavoriteStatus(movieId)
  }
}