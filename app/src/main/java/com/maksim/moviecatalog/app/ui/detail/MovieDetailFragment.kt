package com.maksim.moviecatalog.app.ui.detail

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.maksim.moviecatalog.R
import com.maksim.moviecatalog.app.base.BaseFragment
import com.maksim.moviecatalog.app.ui.model.MovieModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import java.lang.Exception

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class MovieDetailFragment : BaseFragment() {

  companion object {
    val TAG: String = this::class.java.simpleName
  }

  private lateinit var viewModel: MovieDetailViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val args = arguments
    args?.let {
      if (it.containsKey("movie_id")) {
        val movieId = it.getInt("movie_id")
        viewModel = getAppContainer().provideMovieDetailViewModel(this, movieId)
      } else {
        showNoMovieIdAlert(
          "Opps, cannot find movie",
          "Something went wrong and we cannot find the requested movie"
        )
      }
    } ?: showNoMovieIdAlert(
      "Opps, cannot find movie",
      "Something went wrong and we cannot find the requested movie"
    )
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_movie_detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.movieLiveData.observe(this, Observer{ movie ->
      if (movie != null) {
        initScreen(movie)
      } else {
        showNoMovieIdAlert(
          "Opps, cannot find movie",
          "Something went wrong and we cannot find the requested movie"
        )
      }
    })

    favorite_iv.setOnClickListener {
      viewModel.updateFavoriteStatus()
    }

  }


  private fun initScreen(movie: MovieModel) {
    movie_name_tv.text = movie.name
    movie_description_tv.text = movie.description
    movie_year_tv.text = "Year: ${movie.year}"
    movie_rating_tv.text = "Rating: ${movie.rating}"

    val imageRes = if(movie.isFavorite) R.drawable.ic_favorite else R.drawable.ic_not_favorite
    favorite_iv.setImageResource(imageRes)

    showLoading()
    if (movie.posterUrl != null) {
      Picasso.get().load("${movie.getFullPosterUrl()}")
        .placeholder(R.drawable.placeholder)
        .into(movie_image_iv, object : Callback {
          override fun onSuccess() {
            hideLoading()
          }

          override fun onError(e: Exception?) {
            showMessage( "Error loading image: ${e?.message}")
            hideLoading()
          }

        })
    }
  }

  private fun showNoMovieIdAlert(title: String, message: String) {
    AlertDialog.Builder(activity).setTitle(title).setMessage(message)
      .setPositiveButton("Go back", { dialog, which -> activity?.onBackPressed() })
  }

  override fun showLoading() {
    loading_indicator.visibility = View.VISIBLE
  }

  override fun hideLoading() {
    loading_indicator.visibility = View.INVISIBLE
  }

}