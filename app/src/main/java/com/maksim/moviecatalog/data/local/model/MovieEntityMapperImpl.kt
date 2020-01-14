package com.maksim.moviecatalog.data.local.model

import com.maksim.moviecatalog.domain.model.Movie

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class MovieEntityMapperImpl : MovieEntityMapper {
  override fun domainToEntity(movie: Movie): MovieEntity {
    return MovieEntity(
      movie.id,
      movie.dbId ?: 0,
      movie.name,
      movie.description,
      movie.year,
      movie.rating,
      movie.posterUrl,
      movie.isFavorite
    )
  }

  override fun domainToEntity(movies: List<Movie>): List<MovieEntity> {
    return movies.map { movie -> domainToEntity(movie) }
  }

  override fun entityToDomain(entity: MovieEntity): Movie {
    return Movie(
      entity.id,
      entity.dbId,
      entity.name,
      entity.description,
      entity.year,
      entity.rating,
      entity.posterUrl,
      entity.isFavorite
    )
  }

  override fun entityToDomain(entities: List<MovieEntity>): List<Movie> {
    return entities.map { entity -> entityToDomain(entity) }
  }
}