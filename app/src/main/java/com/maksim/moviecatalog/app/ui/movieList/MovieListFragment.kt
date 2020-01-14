package com.maksim.moviecatalog.app.ui.movieList

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.maksim.moviecatalog.R
import com.maksim.moviecatalog.app.base.BaseFragment
import com.maksim.moviecatalog.app.ui.detail.MovieDetailFragment
import com.maksim.moviecatalog.app.ui.model.MovieModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class MovieListFragment : BaseFragment() {

  companion object {
    val TAG: String = this::class.java.simpleName
  }

  private lateinit var viewModel: MovieListViewModel
  private val adapter: MovieAdapter by lazy {
    val layoutManager = LinearLayoutManager(activity)
    val marginItemDecoration = MarginItemDecoration(24)
    movie_list_rc.layoutManager = layoutManager
    movie_list_rc.addItemDecoration(marginItemDecoration)
    MovieAdapter(movieItemClickListener)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_movie_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val args = arguments
    args?.let {
      if (args.containsKey("type")) {
        val listType = args.getSerializable("type") as MovieListType
        initScreen(listType)
      } else {
        showAlert("Something wrong happened", "Cannot load current page")
      }
    } ?: showAlert("Something wrong happened", "Cannot load current page")
  }

  private fun initScreen(type: MovieListType) {


    viewModel = getAppContainer().provideMovieListViewModel(this, type)
    initObservers()
    movie_list_rc.adapter = adapter

    if (!(type == MovieListType.FAVORITE)) {
      show_favorite_movies_btn.visibility = View.VISIBLE
      show_favorite_movies_btn.setOnClickListener { showFavoriteMoviesFragment() }
    }
  }

  private fun initObservers() {

    title_tv.text = viewModel.pageTitle

    viewModel.getLoadingState().observe(this, Observer { showLoading ->
      if (showLoading) {
        showLoading()
      } else {
        hideLoading()
      }
    })

    viewModel.getErrorMessage().observe(this, Observer { errorMessage ->
      showMessage(errorMessage)
    })

    viewModel.movieLiveData.observe(this, Observer { moviesModels ->
      if (moviesModels != null) {
        adapter.updateMovies(moviesModels)
        if (adapter.movies.isEmpty()) {
          no_movies_tv.visibility = View.VISIBLE
        } else {
          no_movies_tv.visibility = View.INVISIBLE
        }
      }
    })
  }

  private val movieItemClickListener: (MovieModel) -> Unit = { movie ->
    showMovieDetailsFragment(movie)
  }

  private fun showMovieDetailsFragment(movie: MovieModel) {
    val bundle = Bundle()
    bundle.putInt("movie_id", movie.id)
    val fragment = MovieDetailFragment()
    fragment.arguments = bundle

    addFragment(fragment, MovieDetailFragment.TAG)
  }

  private fun showFavoriteMoviesFragment() {

    val bundle = Bundle()
    bundle.putSerializable("type", MovieListType.FAVORITE)
    val favoriteFragment = MovieListFragment()
    favoriteFragment.arguments = bundle

    addFragment(favoriteFragment, TAG)
  }

  private fun addFragment(fragment: BaseFragment, tag: String) {
    activity?.supportFragmentManager?.beginTransaction()
      ?.add(R.id.main_container, fragment, tag)
      ?.addToBackStack(tag)
      ?.commit()
  }

  private fun showAlert(title: String, message: String) {
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