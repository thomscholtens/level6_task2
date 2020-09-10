package com.example.level6_task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.level6_task2.R
import com.example.level6_task2.model.Movie
import com.example.level6_task2.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSelectedMovie()
    }

    private fun observeSelectedMovie() {
        viewModel.selected.observe(viewLifecycleOwner, Observer {
            tvTitle.text = it.title
            tvOverview.text = it.overview
            tvRelease.text = it.releaseDate
            tvRating.text = it.rating.toString()
            Glide.with(this@MovieFragment).load(it.getPosterUrl()).into(ivPoster)
            Glide.with(this@MovieFragment).load(it.getBackdropUrl()).into(ivBackdrop)


        })
    }
}