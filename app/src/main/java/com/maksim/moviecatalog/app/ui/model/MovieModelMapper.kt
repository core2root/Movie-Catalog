package com.maksim.moviecatalog.app.ui.model

import com.maksim.moviecatalog.domain.model.Movie

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
interface MovieModelMapper {

  fun domainToModel(movie: Movie): MovieModel

  fun domainToModel(movies: List<Movie>): List<MovieModel>
}