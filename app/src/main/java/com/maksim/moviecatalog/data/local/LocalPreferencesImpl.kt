package com.maksim.moviecatalog.data.local

import android.content.Context

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class LocalPreferencesImpl(context: Context) : LocalPreferences {

  companion object {
    private val FILE_NAME = "com.maksim.moviecatalog.preferences"
    private val FAVORITE_MOVIES_IDS_KEY = "favorite_movies"
  }

  private val prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

  override fun getFavoriteMovieIds(): List<String> {
    return prefs.getStringSet(FAVORITE_MOVIES_IDS_KEY, setOf())?.toList() ?: listOf()
  }

  override fun addFavoriteMovieId(id: String) {
    val set = prefs.getStringSet(FAVORITE_MOVIES_IDS_KEY, setOf())
    if (set != null && !set.contains(id)) {
      set.add(id)
    }

    prefs.edit().putStringSet(FAVORITE_MOVIES_IDS_KEY, set).apply()
  }

  override fun removeFavoriteMovieId(id: String) {
    val idsSet = prefs.getStringSet(FAVORITE_MOVIES_IDS_KEY, setOf())
    if (idsSet != null && idsSet.contains(id)) {
      idsSet.remove(id)
    }
  }
}