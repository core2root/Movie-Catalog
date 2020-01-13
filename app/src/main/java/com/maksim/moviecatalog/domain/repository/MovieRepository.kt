package com.maksim.moviecatalog.domain.repository

import androidx.lifecycle.LiveData
import com.maksim.moviecatalog.domain.model.Movie

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
interface MovieRepository {
  fun getLatestMovies(): LiveData<List<Movie>>
  fun getFavoriteMovies(): LiveData<List<Movie>>
}