package com.maksim.moviecatalog.app.ui.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maksim.moviecatalog.app.di.AppContainer
import com.maksim.moviecatalog.app.ui.model.MovieModelMapperImpl
import java.lang.IllegalArgumentException

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class MovieListViewModelFactory(
  private val appContainer: AppContainer,
  private val listType: MovieListType
) : ViewModelProvider.NewInstanceFactory() {


  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
      return MovieListViewModel(
        listType,
        appContainer.provideGetLatestMoviesUseCase(),
        appContainer.provideGetFavoriteMoviesUseCase(),
        appContainer.provideGetAllMoviesUseCase(),
        MovieModelMapperImpl()
      ) as T
    }
    throw IllegalArgumentException("Wrong view model class")
  }
}