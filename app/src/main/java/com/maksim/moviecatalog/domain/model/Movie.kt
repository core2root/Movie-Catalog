package com.maksim.moviecatalog.domain.model

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
data class Movie(
    val name: String,
    val description: String,
    val year: String,
    val rating: Double,
    val imageUrl: String,
    val isFavorite: Boolean = false
)