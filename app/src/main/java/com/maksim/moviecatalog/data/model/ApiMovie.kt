package com.maksim.moviecatalog.data.model

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
data class ApiMovie(
        val name: String,
        val description: String,
        val year: String,
        val rating: Double,
        val imageUrl: String
)