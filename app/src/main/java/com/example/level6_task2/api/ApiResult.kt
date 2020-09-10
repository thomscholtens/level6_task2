package com.example.level6_task2.api

import com.example.level6_task2.model.Movie
import com.google.gson.annotations.SerializedName

data class ApiResult (
    @SerializedName("results") var movies: List<Movie>
)