package com.example.kotlinyoutube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_home.layoutManager = LinearLayoutManager(this)
        // recyclerview_home.adapter = MainAdapter()
        fetchJSON()
    }

    fun fetchJSON() {
        println("Attempting to fetch JSON from yify")
        val requestURL = "https://yts.mx/api/v2/list_movies.json?sort=rating&limit=3"
        val client = OkHttpClient()
        val request = Request.Builder().url(requestURL).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                val gson = GsonBuilder().create()
                val movieFeed = gson.fromJson(body, MovieFeed::class.java)

                runOnUiThread{
                    recyclerview_home.adapter = MainAdapter(movieFeed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("failed to process request")
            }

        })
    }

    class MovieFeed(val status: String, val status_message: String, val data: Data)
    class Data(val movie_count: Int, val limit: Int, val page_number: Int, val movies: List<Movie>)
    class Movie(val id: Int, val url: String, val title: String, val summary: String)

}