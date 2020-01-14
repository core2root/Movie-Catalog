package com.maksim.moviecatalog.domain.repository

import androidx.lifecycle.LiveData
import com.maksim.moviecatalog.domain.model.Movie

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
interface MovieRepository {
  fun getTrendingMovies(): LiveData<Resource<Boolean>>
  fun getFavoriteMovies(): LiveData<List<Movie>>
  fun getMovie(movieId: Int): LiveData<Movie?>
  fun getAllMovies(): LiveData<List<Movie>>
  fun updateMovieFavoriteStatus(movieId: Int)
}