package com.maksim.moviecatalog.data.local.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */

@Entity

data class MovieEntity(
  val id: Int,
  @Id
  var dbId: Long = 0,
  val name: String?,
  val description: String?,
  val year: String,
  val rating: Double?,
  val posterUrl: String?,
  var isFavorite: Boolean
)