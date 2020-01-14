package com.maksim.moviecatalog.data.local.model

import com.maksim.moviecatalog.domain.model.Movie

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
interface MovieEntityMapper {
  fun domainToEntity(movie: Movie): MovieEntity
  fun domainToEntity(movies: List<Movie>): List<MovieEntity>

  fun entityToDomain(entity: MovieEntity): Movie
  fun entityToDomain(entities: List<MovieEntity>): List<Movie>
}