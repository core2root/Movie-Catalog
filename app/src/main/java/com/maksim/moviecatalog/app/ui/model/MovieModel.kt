package com.maksim.moviecatalog.app.ui.model

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
data class MovieModel(
  val id: Int,
  val dbId: Long?,
  val name: String?,
  val description: String?,
  val year: String,
  val rating: Double?,
  val posterUrl: String? ,
  val isFavorite: Boolean
) {

  private val POSTER_URL_PREFIX = "https://image.tmdb.org/t/p/w500/"

  /*fun getYear(): String? {
    return releaseDate?.let { it.substring(0, it.indexOf("-")) } ?: "unknown"
  }*/

  fun getFullPosterUrl(): String{
    return "$POSTER_URL_PREFIX$posterUrl"
  }

}