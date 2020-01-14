package com.maksim.moviecatalog.data.remote

import android.util.Log
import com.maksim.moviecatalog.data.model.ApiMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class MovieServiceImpl : MovieService {

  private val BASE_URL = "https://api.themoviedb.org/3/"
  private val apiKey = "cdd4b968df3a157821ce8f0032894c06"
  private val movieApi: MovieApi

  init {
    val retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    movieApi = retrofit.create(MovieApi::class.java)
  }

  override fun getTrendingMovies(callback: MovieService.Callback<List<ApiMovie>>) {
    movieApi.getTrendingMovies(apiKey).enqueue(object : Callback<MovieApiResult> {
      override fun onResponse(call: Call<MovieApiResult>, response: Response<MovieApiResult>) {
        handleResponse(response, callback)
      }

      override fun onFailure(call: Call<MovieApiResult>, t: Throwable) {
        Log.e("LogTag", "error: ${t.message}")
        val message = t.message ?: "Error requesting trending movies"
        callback.onError(message)
      }
    })
  }

  private fun handleResponse(
    response: Response<MovieApiResult>,
    callback: MovieService.Callback<List<ApiMovie>>
  ) {
    try {
      if (response.isSuccessful) {
        val result = response.body()!!
        callback.onSuccess(result.results)
      } else {
        callback.onError("Error getting movies code: ${response.code()}")
      }
    } catch (e: Exception) {
      callback.onError(e.message ?: "Unknown error")
    }
  }
}