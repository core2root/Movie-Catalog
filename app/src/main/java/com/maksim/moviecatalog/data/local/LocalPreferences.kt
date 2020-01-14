package com.maksim.moviecatalog.data.local

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
interface LocalPreferences {

  fun getFavoriteMovieIds(): List<String>
  fun addFavoriteMovieId(id: String)
  fun removeFavoriteMovieId(id: String)
}