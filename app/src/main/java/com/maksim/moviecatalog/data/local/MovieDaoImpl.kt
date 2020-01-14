package com.maksim.moviecatalog.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.maksim.moviecatalog.data.local.model.MovieEntity
import com.maksim.moviecatalog.data.local.model.MovieEntityMapper
import com.maksim.moviecatalog.data.local.model.MovieEntity_
import com.maksim.moviecatalog.domain.model.Movie
import io.objectbox.Box
import io.objectbox.android.ObjectBoxLiveData
import io.objectbox.query.Query

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class MovieDaoImpl(private val box: Box<MovieEntity>, private val mapper: MovieEntityMapper) :
  MovieDao {

  override fun getFavoriteMovies(): LiveData<List<Movie>> {
    val query: Query<MovieEntity> = box.query().equal(MovieEntity_.isFavorite, true).build()
    val liveData = ObjectBoxLiveData(query)
    return Transformations.map(liveData) { entities ->
      mapper.entityToDomain(entities)
    }
  }

  override fun getAllMovies(): LiveData<List<Movie>> {
    val query = box.query().build()
    return Transformations.map(ObjectBoxLiveData(query)) { entities ->
      mapper.entityToDomain(entities)
    }
  }

  override fun getMovie(movieId: Int): LiveData<Movie?> {
    val liveData = ObjectBoxLiveData(box.query().equal(MovieEntity_.id, movieId.toLong()).build())
    return Transformations.map(liveData) { entities ->
      if (entities.isNotEmpty()) {
        mapper.entityToDomain(entities[0])
      } else {
        null
      }
    }
  }

  override fun saveMovies(movies: List<Movie>) {
    //Take all ids from new movies
    val ids = movies.map { it.id }.toIntArray()

    val localMovies = box.query().`in`(MovieEntity_.id, ids).build().find()
    val newMovies = mapper.domainToEntity(movies)
    for (movie in newMovies) {
      for (localMovie in localMovies) {
        if (movie.id == localMovie.id && localMovie.isFavorite) {
          movie.isFavorite = true
          break
        }
      }
    }

    box.removeAll()
    box.put(newMovies)

  }

  override fun updateMovieFavoriteStatus(movieId: Int) {
    val query = box.query().equal(MovieEntity_.id, movieId.toLong()).build()
    val result = query.findFirst()
    if (result != null) {
      result.isFavorite = !result.isFavorite
      box.put(result)
    }
  }
}