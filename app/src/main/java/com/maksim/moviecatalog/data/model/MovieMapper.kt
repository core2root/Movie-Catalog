package com.maksim.moviecatalog.data.model

import com.maksim.moviecatalog.domain.model.Movie

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
interface MovieMapper {
  fun apiToDomain(api: ApiMovie): Movie
  fun apiToDomain(api: List<ApiMovie>): List<Movie>
}