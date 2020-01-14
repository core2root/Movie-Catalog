package com.maksim.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maksim.moviecatalog.data.local.MovieDao
import com.maksim.moviecatalog.data.model.ApiMovie
import com.maksim.moviecatalog.data.model.MovieMapper
import com.maksim.moviecatalog.data.remote.MovieService
import com.maksim.moviecatalog.domain.model.Movie
import com.maksim.moviecatalog.domain.repository.MovieRepository
import com.maksim.moviecatalog.domain.repository.Resource


/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class MovieRepositoryImpl(
  private val movieService: MovieService,
  private val movieDao: MovieDao,
  private val mapper: MovieMapper
) : MovieRepository {

  override fun getMovie(movieId: Int): LiveData<Movie?> {
    return movieDao.getMovie(movieId)
  }

  override fun getAllMovies(): LiveData<List<Movie>>{
    return movieDao.getAllMovies()
  }

  override fun getTrendingMovies(): LiveData<Resource<Boolean>> {
    val result = MutableLiveData<Resource<Boolean>>()

    movieService.getTrendingMovies(object : MovieService.Callback<List<ApiMovie>> {
      override fun onSuccess(data: List<ApiMovie>) {
        val domainMovies = mapper.apiToDomain(data)
        movieDao.saveMovies(domainMovies)
        result.postValue(Resource.success(true))
      }

      override fun onError(message: String) {
        result.postValue(Resource.error(message))
      }
    })
    return result
  }

  override fun getFavoriteMovies(): LiveData<List<Movie>> {
    return movieDao.getFavoriteMovies()
  }

  override fun updateMovieFavoriteStatus(movieId: Int) {
    movieDao.updateMovieFavoriteStatus(movieId)
  }
}