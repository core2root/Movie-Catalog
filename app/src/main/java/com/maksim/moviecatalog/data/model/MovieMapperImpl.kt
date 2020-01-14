package com.maksim.moviecatalog.data.model

import com.maksim.moviecatalog.domain.model.Movie

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class MovieMapperImpl : MovieMapper {
  override fun apiToDomain(api: ApiMovie): Movie {
    val name = api.name ?: api.title ?: api.originalName ?: "Unknown"
    val year = getYearFromDate(api.releaseDate) ?: getYearFromDate(api.firstAirDate) ?: "Unknown"
    return Movie(api.id, 0, name, api.description, year, api.rating, api.posterUrl)
  }

  override fun apiToDomain(api: List<ApiMovie>): List<Movie> {
    return api.map { item -> apiToDomain(item) }
  }

  private fun getYearFromDate(date: String?): String? {
    if (date == null) return null

    return date.substring(0, date.indexOf("-"))
  }

}