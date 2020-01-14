package com.maksim.moviecatalog.app.di


import androidx.lifecycle.ViewModelProviders
import com.maksim.moviecatalog.app.base.BaseFragment
import com.maksim.moviecatalog.app.ui.detail.MovieDetailViewModelFactory
import com.maksim.moviecatalog.app.ui.detail.MovieDetailViewModel
import com.maksim.moviecatalog.app.ui.movieList.MovieListType
import com.maksim.moviecatalog.app.ui.movieList.MovieListViewModel
import com.maksim.moviecatalog.app.ui.movieList.MovieListViewModelFactory
import com.maksim.moviecatalog.data.MovieRepositoryImpl
import com.maksim.moviecatalog.data.local.MovieDao
import com.maksim.moviecatalog.data.local.MovieDaoImpl
import com.maksim.moviecatalog.data.local.model.MovieEntity
import com.maksim.moviecatalog.data.local.model.MovieEntityMapper
import com.maksim.moviecatalog.data.local.model.MovieEntityMapperImpl
import com.maksim.moviecatalog.data.remote.MovieService
import com.maksim.moviecatalog.data.remote.MovieServiceImpl
import com.maksim.moviecatalog.data.model.MovieMapper
import com.maksim.moviecatalog.data.model.MovieMapperImpl
import com.maksim.moviecatalog.domain.repository.MovieRepository
import com.maksim.moviecatalog.domain.useCase.*
import io.objectbox.BoxStore

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class AppContainer(boxStore: BoxStore) {

  private val movieRepository: MovieRepository

  init {
    val movieMapper: MovieMapper = MovieMapperImpl()
    val movieService: MovieService = MovieServiceImpl()
    val movieEntityMapper: MovieEntityMapper = MovieEntityMapperImpl()
    val movieDao: MovieDao =
      MovieDaoImpl(boxStore.boxFor(MovieEntity::class.java), movieEntityMapper)
    movieRepository = MovieRepositoryImpl(movieService, movieDao, movieMapper)
  }

  fun provideMovieDetailViewModel(fragment: BaseFragment, movieId: Int): MovieDetailViewModel {
    val factory = MovieDetailViewModelFactory(this, movieId)
    return ViewModelProviders.of(fragment, factory).get(MovieDetailViewModel::class.java)
  }

  fun provideMovieListViewModel(fragment: BaseFragment, type: MovieListType): MovieListViewModel {
    val factory = MovieListViewModelFactory(this, type)
    return ViewModelProviders.of(fragment, factory).get(MovieListViewModel::class.java)
  }

  //Use case providers

  fun provideUpdateMovieFavoriteStatusUseCase(): UpdateMovieFavoriteStatusUseCase {
    return UpdateMovieFavoriteStatusUseCase(movieRepository)
  }

  fun provideGetMovieByIdUseCase(): GetMovieByIdUseCase {
    return GetMovieByIdUseCase(movieRepository)
  }

  fun provideGetLatestMoviesUseCase(): GetTrendingMoviesUseCase {
    return GetTrendingMoviesUseCase(movieRepository)
  }

  fun provideGetFavoriteMoviesUseCase(): GetFavoriteMoviesUseCase {
    return GetFavoriteMoviesUseCase(movieRepository)
  }

  fun provideGetAllMoviesUseCase(): GetAllMoviesLiveDataUseCase {
    return GetAllMoviesLiveDataUseCase(movieRepository)
  }

}