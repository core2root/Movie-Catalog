package com.maksim.moviecatalog.data.remote

import com.maksim.moviecatalog.data.model.ApiMovie

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class MovieApiResult(val total_pages: Int, val results: List<ApiMovie>)