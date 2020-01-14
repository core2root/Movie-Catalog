package com.maksim.moviecatalog.app.ui.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maksim.moviecatalog.R
import com.maksim.moviecatalog.app.ui.model.MovieModel
import com.squareup.picasso.Picasso

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class MovieAdapter(
  val listener: (MovieModel) -> Unit,
  var movies: List<MovieModel> = listOf()
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      LayoutInflater.from(parent.context).inflate(
        R.layout.movie_item,
        parent,
        false
      )
    )
  }

  override fun getItemCount(): Int {
    return movies.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val movie = movies[position]
    holder.name.text = movie.name

    Picasso.get().load(movie.getFullPosterUrl()).into(holder.image)

    holder.itemView.setOnClickListener { listener(movie) }
  }

  fun updateMovies(movies: List<MovieModel>) {
    this.movies = movies
    notifyDataSetChanged()
  }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image: ImageView = view.findViewById(R.id.movie_image_iv)
    val name: TextView = view.findViewById(R.id.movie_name_tv)
  }
}