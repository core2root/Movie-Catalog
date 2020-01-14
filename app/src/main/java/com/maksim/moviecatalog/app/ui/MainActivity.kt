package com.maksim.moviecatalog.app.ui

import android.os.Bundle
import com.maksim.moviecatalog.R
import com.maksim.moviecatalog.app.base.BaseActivity
import com.maksim.moviecatalog.app.ui.movieList.MovieListFragment
import com.maksim.moviecatalog.app.ui.movieList.MovieListType

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {

      val bundle = Bundle()
      bundle.putSerializable("type", MovieListType.ALL)
      val trendingMoviesFragment = MovieListFragment()
      trendingMoviesFragment.arguments = bundle

      supportFragmentManager.beginTransaction()
        .replace(R.id.main_container, trendingMoviesFragment, MovieListFragment.TAG)
        .addToBackStack(MovieListFragment.TAG)
        .commit()
    }

  }

  override fun onBackPressed() {
    val fragments = supportFragmentManager.fragments
    if (fragments.size == 1 && fragments[0] is MovieListFragment) {
      finish()
    } else {
      super.onBackPressed()
    }
  }

}
