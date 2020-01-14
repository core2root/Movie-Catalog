package com.maksim.moviecatalog.app.ui.model

import com.maksim.moviecatalog.domain.model.Movie

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class MovieModelMapperImpl : MovieModelMapper {
  override fun domainToModel(movie: Movie): MovieModel {
    return MovieModel(
      movie.id,
      movie.dbId,
      movie.name,
      movie.description,
      movie.year,
      movie.rating,
      movie.posterUrl,
      movie.isFavorite
    )
  }

  override fun domainToModel(movies: List<Movie>): List<MovieModel> {
    return movies.map { movie -> domainToModel(movie) }
  }
}