package com.example.submission1wahyu.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1wahyu.R
import com.example.submission1wahyu.core.data.Resource.*
import com.example.submission1wahyu.core.domain.model.Movie
import com.example.submission1wahyu.core.ui.MovieAdapter
import com.example.submission1wahyu.databinding.ActivityMainBinding
import com.example.submission1wahyu.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter()
        movieViewModel.movie.observe(this, { dataList ->
            if (dataList != null) {
                when (dataList) {
                    is Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.setData(dataList.data)
                    }
                    is Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, dataList.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        setupInterface()
    }

    private fun setupInterface(){
        movieAdapter = MovieAdapter()

        binding.apply{
            rvMovie.layoutManager = LinearLayoutManager(this@MainActivity)
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = movieAdapter
        }

        movieAdapter.onItemClick = { selectedData -> setSelectedMovie(selectedData) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                val uri = Uri.parse("movieqapp://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setSelectedMovie(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.MOVIE, movie)
        }
        startActivity(intent)
    }
}