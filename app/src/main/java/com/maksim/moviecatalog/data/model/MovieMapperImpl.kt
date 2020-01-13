package com.maksim.moviecatalog.data.model

import com.maksim.moviecatalog.domain.model.Movie

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class MovieMapperImpl : MovieMapper {
  override fun apiToDomain(api: ApiMovie): Movie {
    return Movie(api.name, api.description, api.year, api.rating, api.imageUrl)
  }

  override fun apiToDomain(api: List<ApiMovie>): List<Movie> {
    return api.map { item -> apiToDomain(item) }
  }
}