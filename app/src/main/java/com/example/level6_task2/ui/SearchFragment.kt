package com.example.level6_task2.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.level6_task2.R
import com.example.level6_task2.adapter.MovieAdapter
import com.example.level6_task2.model.Movie
import com.example.level6_task2.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchFragment : Fragment() {
    private val viewModel: MovieViewModel by activityViewModels()
    private val movies = arrayListOf<Movie>()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter(movies, ::onMovieClick)
        rvMovies.layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
        rvMovies.adapter = movieAdapter
        onSubmit()
        observeMovie()

    }

    private fun onSubmit() {
        btnSubmit.setOnClickListener {
            viewModel.getMoviesByYear(etYear.text.toString())
        }
    }

    private fun onMovieClick(movie: Movie) {
        viewModel.select(movie)
        findNavController().apply { movie }   .navigate(R.id.action_searchFragment_to_movieFragment)

    }

    private fun observeMovie() {
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }



}