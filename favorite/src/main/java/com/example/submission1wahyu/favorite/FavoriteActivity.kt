package com.example.submission1wahyu.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1wahyu.R
import com.example.submission1wahyu.core.domain.model.Movie
import com.example.submission1wahyu.core.ui.MovieAdapter
import com.example.submission1wahyu.databinding.ActivityFavoriteBinding
import com.example.submission1wahyu.detail.DetailActivity
import com.example.submission1wahyu.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        movieAdapter = MovieAdapter()
        favoriteViewModel.favMovie.observe(this, { dataList ->
            if (dataList != null) {
                movieAdapter.setData(dataList)
                binding.viewEmpty.root.visibility = if (dataList.isNotEmpty()) View.GONE else View.VISIBLE
            }
        })

        showRecyclerView()
        setActionBar()
    }

    private fun setActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getString(R.string.favorite)
        }
    }

    private fun showRecyclerView() {
        movieAdapter = MovieAdapter()

        binding.apply {
            rvFavMovie.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavMovie.setHasFixedSize(true)
            rvFavMovie.adapter = movieAdapter
        }

        movieAdapter.onItemClick = { selectedData -> setSelectedMovie(selectedData) }
    }

    private fun setSelectedMovie(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.MOVIE, movie)
        }
        startActivity(intent)
    }
}