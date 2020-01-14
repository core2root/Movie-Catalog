package com.maksim.moviecatalog.app.di

import android.content.Context
import com.maksim.moviecatalog.app.ui.ViewModelFactory
import com.maksim.moviecatalog.data.MovieRepositoryImpl
import com.maksim.moviecatalog.data.remote.MovieService
import com.maksim.moviecatalog.data.remote.MovieServiceImpl
import com.maksim.moviecatalog.data.model.MovieMapper
import com.maksim.moviecatalog.data.model.MovieMapperImpl
import com.maksim.moviecatalog.domain.repository.MovieRepository
import com.maksim.moviecatalog.domain.useCase.GetFavoriteMoviesUseCase
import com.maksim.moviecatalog.domain.useCase.GetLatestMoviesUseCase

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class AppContainer(context: Context) {

  private val movieRepository: MovieRepository
  private val viewModelFactory: ViewModelFactory = ViewModelFactory(this)

  init {
    val movieMapper: MovieMapper = MovieMapperImpl()
    val movieService: MovieService =
      MovieServiceImpl()
    movieRepository = MovieRepositoryImpl(movieService, movieMapper)
  }


  //Use case providers

  fun provideGetLatestMoviesUseCase(): GetLatestMoviesUseCase {
    return GetLatestMoviesUseCase(movieRepository)
  }

  fun provideGetFavoriteMoviesUseCase(): GetFavoriteMoviesUseCase {
    return GetFavoriteMoviesUseCase(movieRepository)
  }

}