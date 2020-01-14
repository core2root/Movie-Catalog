package com.maksim.moviecatalog.app.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.maksim.moviecatalog.app.ui.model.MovieModel
import com.maksim.moviecatalog.app.ui.model.MovieModelMapper
import com.maksim.moviecatalog.domain.useCase.GetMovieByIdUseCase
import com.maksim.moviecatalog.domain.useCase.UpdateMovieFavoriteStatusUseCase

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class MovieDetailViewModel(
  private val movieId: Int,
  getMovieByIdUseCase: GetMovieByIdUseCase,
  private val updateMovieFavoriteStatusUseCase: UpdateMovieFavoriteStatusUseCase,
  private val mapper: MovieModelMapper
) : ViewModel() {

  val movieLiveData: LiveData<MovieModel?>

  init {
    movieLiveData = Transformations.map(getMovieByIdUseCase.execute(movieId)) { movie ->
      if (movie != null) {
        mapper.domainToModel(movie)
      } else {
        null
      }
    }
  }

  fun updateFavoriteStatus() {
    updateMovieFavoriteStatusUseCase.execute(movieId)
  }

}