package com.maksim.moviecatalog.data.remote

import com.maksim.moviecatalog.data.model.ApiMovie

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
interface MovieService {

  fun getTrendingMovies(callback: Callback<List<ApiMovie>>)

  interface Callback<T> {
    fun onSuccess(data: T)
    fun onError(message: String)
  }
}