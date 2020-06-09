package com.example.kotlinyoutube

class MovieFeed(val status: String, val status_message: String, val data: Data)

class Data(val movie_count: Int, val limit: Int, val page_number: Int, val movies: List<Movie>)

class Movie(val id: Int, val url: String, val title: String, val summary: String,
            val rating: Float, val medium_cover_image: String, val large_cover_image: String)