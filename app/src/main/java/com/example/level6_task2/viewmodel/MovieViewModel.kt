package com.example.level6_task2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.level6_task2.api.MovieRepository
import com.example.level6_task2.model.Movie
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val movieRepository = MovieRepository()

    /**
     * This property points direct to the LiveData in the repository, that value
     * get's updated when user clicks FAB. This happens through the getTriviaNumber() in this class :)
     */
    val movie = movieRepository.movies

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from view for error showing
     * Encapsulation :)
     */
    val errorText: LiveData<String>
        get() = _errorText


    private val _selected: MutableLiveData<Movie> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val selected: LiveData<Movie> get() = _selected


    fun select(item: Movie) {
        _selected.value = item
    }

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getMoviesByYear(year: String) {
        viewModelScope.launch {
            try {
                //the triviaRepository sets it's own livedata property
                //our own trivia LiveData property points to te one in that repository
                movieRepository.getMoviesFromYear(year)
            } catch (error: MovieRepository.TriviaRefreshError) {
                _errorText.value = error.message
                Log.e("Trivia error", error.cause.toString())
            }
        }
    }
}