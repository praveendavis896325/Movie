package com.praveen.omdb.movies.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.praveen.omdb.R
import com.praveen.omdb.movies.data.Movie
import kotlinx.android.synthetic.main.fragment_detail.*

class MovieAdapter : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(MovieModelComparator) {

    private lateinit var listener: OnItemClick

    fun setListener(mOnItemClick: OnItemClick) {
        listener = mOnItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_view, parent, false)
        return MovieViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as MovieViewHolder
        val movie = getItem(position)
        movie?.Poster?.let {
            Glide.with(viewHolder.itemView.context)
                .load(Uri.parse("$it"))
                .into(viewHolder.ivPoster)
            viewHolder.ivPoster.setOnClickListener { listener.onMovieClicked(movie) }
        }
        viewHolder.tvTitle.text = movie?.Title
        viewHolder.tvYear.text = "Year : "+movie?.Year
        viewHolder.tvimdbID.text =  "imdbID : "+movie?.imdbID
        viewHolder.tvType.text =  "Type : "+movie?.Type

    }


    inner class MovieViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPoster: ImageView = itemView.findViewById(R.id.movieImg) as ImageView
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle) as TextView
        var tvYear: TextView = itemView.findViewById(R.id.tv_release_date) as TextView
        var tvimdbID: TextView = itemView.findViewById(R.id.tv_imdb) as TextView
        var tvType: TextView = itemView.findViewById(R.id.tvType) as TextView
    }
    interface OnItemClick {
        fun onMovieClicked(movieEntity: Movie)
    }


    companion object {
        private val MovieModelComparator = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }


}
