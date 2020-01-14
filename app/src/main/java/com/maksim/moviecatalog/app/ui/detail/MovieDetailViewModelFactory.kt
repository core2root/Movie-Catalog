package com.maksim.moviecatalog.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maksim.moviecatalog.app.di.AppContainer
import com.maksim.moviecatalog.app.ui.model.MovieModelMapperImpl
import java.lang.IllegalArgumentException

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class MovieDetailViewModelFactory(private val appContainer: AppContainer, private val movieId: Int) :
  ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    when {
      modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
        return MovieDetailViewModel(
          movieId,
          appContainer.provideGetMovieByIdUseCase(),
          appContainer.provideUpdateMovieFavoriteStatusUseCase(),
          MovieModelMapperImpl()
        ) as T
      }

    }

    throw IllegalArgumentException("Wrong view model class")
  }


}