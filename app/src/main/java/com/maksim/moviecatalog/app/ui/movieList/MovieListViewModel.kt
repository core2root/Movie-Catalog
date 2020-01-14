package com.maksim.moviecatalog.app.ui.movieList

import androidx.lifecycle.*
import com.maksim.moviecatalog.app.ui.model.MovieModel
import com.maksim.moviecatalog.app.ui.model.MovieModelMapper
import com.maksim.moviecatalog.domain.repository.Resource
import com.maksim.moviecatalog.domain.useCase.GetAllMoviesLiveDataUseCase
import com.maksim.moviecatalog.domain.useCase.GetFavoriteMoviesUseCase
import com.maksim.moviecatalog.domain.useCase.GetTrendingMoviesUseCase

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class MovieListViewModel(
  listType: MovieListType,
  private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
  getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
  getAllMoviesLiveDataUseCase: GetAllMoviesLiveDataUseCase,
  private val mapper: MovieModelMapper
) : ViewModel() {

  val movieLiveData: LiveData<List<MovieModel>>
  private val loadingState = MutableLiveData<Boolean>()
  private val errorMessage = MutableLiveData<String>()
  val pageTitle: String

  init {
    pageTitle = when (listType) {
      MovieListType.ALL -> {
        setLoadingState(true)
        movieLiveData = Transformations.map(getAllMoviesLiveDataUseCase.execute()) { movies ->
          setLoadingState(false)
          if (movies.isEmpty()) {
            refreshMovies()
          }
          mapper.domainToModel(movies)
        }
        "ALL movies"
      }
      MovieListType.FAVORITE -> {

        movieLiveData = Transformations.map(getFavoriteMoviesUseCase.execute()) { movies ->
          setLoadingState(false)
          mapper.domainToModel(movies)
        }
        "Favorite movies"
      }
    }
  }

  private fun refreshMovies() {
    setLoadingState(true)
    val liveData = getTrendingMoviesUseCase.execute()
    liveData.observeForever(object : Observer<Resource<Boolean>> {
      override fun onChanged(result: Resource<Boolean>) {
        setLoadingState(false)
        when (result.status) {
          Resource.Status.SUCCESS -> {
            //nothing to do here
          }
          Resource.Status.ERROR -> {
            setErrorMessage(result.error ?: "unknown error")
          }
        }

        liveData.removeObserver(this)
      }
    })
  }

  fun getLoadingState(): LiveData<Boolean> {
    return loadingState
  }

  fun getErrorMessage(): LiveData<String> {
    return errorMessage
  }

  private fun setErrorMessage(message: String) {
    errorMessage.postValue(message)
  }

  private fun setLoadingState(showLoading: Boolean) {
    loadingState.postValue(showLoading)
  }


}