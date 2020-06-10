package com.example.kotlinyoutube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_summary.*

class MovieSummaryActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_movie_summary)

        // change the bar title of the activity
        val barTitle = intent.getStringExtra(CustomViewHolder.MOVIE_TITLE)
        supportActionBar?.title = barTitle

        val movieId = intent.getIntExtra(CustomViewHolder.MOVIE_ID, -1)
        val movieRating = intent.getStringExtra(CustomViewHolder.MOVIE_RATING)
        val movieSummary = intent.getStringExtra(CustomViewHolder.MOVIE_SUMMARY)
        val movieTitle = intent.getStringExtra(CustomViewHolder.MOVIE_TITLE)
        val largeImageURL = intent.getStringExtra(CustomViewHolder.LARGE_IMAGE_LINK)


        // Putting imported data into the UI

        // Image and placeholder
        Picasso.get()
            .load(largeImageURL)
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .resize(400, 500)
            .into(imageview_large_movie)

        // Title
        textview_movie_name_summary.text = movieTitle

        // Rating
        textview_movie_rating_summary.text = movieRating.toString()

        // Summary
        textview_movie_summary_long.text = movieSummary
    }
}