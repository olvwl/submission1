package com.example.submission1wahyu.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.submission1wahyu.R
import com.example.submission1wahyu.core.data.source.remote.network.NetwokInfo.IMAGE_URL
import com.example.submission1wahyu.core.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.submission1wahyu.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    
    companion object {
        const val MOVIE = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(MOVIE) as Movie
        showMovieDetails(detailMovie)
    }

    private fun showMovieDetails(movie: Movie) {
        supportActionBar?.title = movie.title

        binding.apply {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_loading)
            requestOptions.error(R.drawable.ic_error)

            Glide.with(this@DetailActivity)
                .load(IMAGE_URL + movie.poster)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(ivPosterDetail)

            tvTitleDetail.text = movie.title
            tvTitleDetail.isSelected = true
            tvTitleDetail.isSingleLine = true
            tvDescriptionDetail.text = movie.description
            tvReleaseDetail.text = movie.date
            tvRatingDetail.text = movie.rating
        }

        var stateMovie = movie.favorite

        setFavoriteState(stateMovie)
        binding.tbFavorite.setOnClickListener {
            stateMovie = !stateMovie
            setFavoriteState(stateMovie)
            viewModel.setFavoriteMovie(movie, stateMovie)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setFavoriteState(state: Boolean) {
        binding.apply {
            tbFavorite.isChecked = state
        }
    }
}