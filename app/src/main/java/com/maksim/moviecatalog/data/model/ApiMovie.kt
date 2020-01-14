package com.maksim.moviecatalog.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
data class ApiMovie(
  val id: Int,
  val originalName: String?,
  val title: String?,
  val name: String?,
  @SerializedName("overview")
  val description: String,
  @SerializedName("release_date")
  val releaseDate: String,
  @SerializedName("first_air_date")
  val firstAirDate: String?,
  @SerializedName("vote_average")
  val rating: Double?,
  @SerializedName("poster_path")
  val posterUrl: String?
)