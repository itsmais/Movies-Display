package com.example.kotlinyoutube


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.*
import kotlinx.android.synthetic.main.movie_row.view.*


class MainAdapter (val movieFeed: MovieFeed): RecyclerView.Adapter<CustomViewHolder>(){
    // val movieTitles = listOf<String>("first movie","second movie","third movie")
    // number of items inside the RecyclerView
    override fun getItemCount(): Int{
        return movieFeed.data.movies.count()
    }

    // required method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // creating a view
        // 1- create a new layout resource movie_row.xml. It's a constraintlayout that has an image view and text view
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.movie_row, parent, false)



        return CustomViewHolder(cellForRow)

    }

    // required method
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // val movieTitle = movieTitles.get(position)
        val movie = movieFeed.data.movies.get(position)
        holder?.view?.textview_movie_name.text = movie.title
        holder?.view?.textview_movie_summary.text = movie.summary
        holder?.view?.textview_movie_rating.text = movie.rating.toString()

        // getting a medium sized image thumbnail of the movie
        val movieThumbnailImageURL = movie.medium_cover_image
        val movieThumbnail = holder?.view?.imageview_movie

        Picasso.get()
            .load(movieThumbnailImageURL)
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .resize(200, 220)
            .into(movieThumbnail)

        holder?.movie = movie
    }
}

// we need this to pass it to the MainAdapter
class CustomViewHolder (val view: View, var movie: Movie? = null): RecyclerView.ViewHolder(view){
    companion object{ // key values
        val MOVIE_TITLE = "Movie Title"
        val MOVIE_ID = "123"
        val MOVIE_SUMMARY = "something"
        val MOVIE_RATING = "8"
        val LARGE_IMAGE_LINK = "url goes here"
    }
    init {
        view.setOnClickListener{

            // println("Clicking is working")
            val intent = Intent(view.context, MovieSummaryActivity::class.java)
            intent.putExtra(MOVIE_TITLE, movie?.title)
            intent.putExtra(MOVIE_ID, movie?.id)
            intent.putExtra(MOVIE_SUMMARY, movie?.summary)
            intent.putExtra(MOVIE_RATING, movie?.rating.toString())
            intent.putExtra(LARGE_IMAGE_LINK, movie?.large_cover_image)

            view.context.startActivity(intent)
        }
    }
}