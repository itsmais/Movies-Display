package com.example.kotlinyoutube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.*
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter (val movieFeed: MainActivity.MovieFeed): RecyclerView.Adapter<CustomViewHolder>(){
    // val movieTitles = listOf<String>("first movie","second movie","third movie")
    // number of items inside the RecyclerView
    override fun getItemCount(): Int{
        return movieFeed.data.movies.count()
    }

    // required method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // creating a view
        // 1- create a new layout resource video_row.xml. It's a constraintlayout that has an image view and text view
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    // required method
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // val movieTitle = movieTitles.get(position)
        val movie = movieFeed.data.movies.get(position)
        holder?.view?.textview_movie_name.text = movie.title
        holder?.view?.textview_movie_summary.text = movie.summary
        holder?.view?.textview_movie_rating.text = movie.rating.toString()
        val movieImageURL = movie.medium_cover_image
        val movieThumbnail = holder?.view?.imageview_movie
        Picasso.get()
            .load(movieImageURL)
            .resize(200, 220)
            .centerCrop()
            .into(movieThumbnail)
    }
}

// we need this to pass it to the MainAdapter
class CustomViewHolder (val view: View): RecyclerView.ViewHolder(view){

}