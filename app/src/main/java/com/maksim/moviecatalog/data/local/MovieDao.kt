package com.maksim.moviecatalog.data.local

import androidx.lifecycle.LiveData
import com.maksim.moviecatalog.domain.model.Movie

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
interface MovieDao {

  fun getAllMovies(): LiveData<List<Movie>>
  fun getMovie(movieId: Int): LiveData<Movie?>
  fun getFavoriteMovies(): LiveData<List<Movie>>
  fun updateMovieFavoriteStatus(movieId: Int)
  fun saveMovies(movies: List<Movie>)

}