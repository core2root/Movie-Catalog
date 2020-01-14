package com.maksim.moviecatalog.data

import androidx.lifecycle.LiveData
import com.maksim.moviecatalog.data.model.MovieMapper
import com.maksim.moviecatalog.data.remote.MovieService
import com.maksim.moviecatalog.domain.model.Movie
import com.maksim.moviecatalog.domain.repository.MovieRepository

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class MovieRepositoryImpl(
  private val movieService: MovieService,
  private val mapper: MovieMapper) : MovieRepository {


  override fun getLatestMovies(): LiveData<List<Movie>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getFavoriteMovies(): LiveData<List<Movie>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}