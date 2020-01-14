package com.maksim.moviecatalog.data.remote

import com.maksim.moviecatalog.data.model.ApiMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
interface MovieApi {

  @GET("trending/all/day")
  fun getTrendingMovies(
    @Query("api_key") apiKey: String,
    @Query("sort_by") sortBy: String = "release_date.desc"
  ): Call<MovieApiResult>


}