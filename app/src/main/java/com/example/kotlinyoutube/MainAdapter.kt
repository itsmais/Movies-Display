package com.example.kotlinyoutube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter (val movieFeed: MainActivity.MovieFeed): RecyclerView.Adapter<CustomViewHolder>(){
    val movieTitles = listOf<String>("first movie","second movie","third movie")
    // number of items
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
    }
}

// we need this to pass it to the MainAdapter
class CustomViewHolder (val view: View): RecyclerView.ViewHolder(view){

}