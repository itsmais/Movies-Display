package com.example.kotlinyoutube

import android.R.*
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_home.layoutManager = LinearLayoutManager(this)
        // recyclerview_home.adapter = MainAdapter()
        mainAdapter = MainAdapter()
        recyclerview_home.adapter = mainAdapter

        getMoviesFromYTS()
    }

    fun getMoviesFromYTS() {
        println("Attempting to fetch JSON from yify")
        val requestURL = "https://yts.mx/api/v2/list_movies.json?sort=rating&limit=15"
        val client = OkHttpClient()

        val request = Request.Builder().url(requestURL).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                val gson = GsonBuilder().create()
                val movieFeed = gson.fromJson(body, MovieFeed::class.java)

                runOnUiThread{
                    mainAdapter?.movies = movieFeed.data.movies
                    mainAdapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("failed to process request")
            }

        })
    }

    fun searchMoviesInYTS(searchQuery: String) {
        println("Attempting to fetch JSON from yify")
        val requestURL = "https://yts.mx/api/v2/list_movies.json?query_term="
        val client = OkHttpClient()
        val request = Request.Builder().url(requestURL+searchQuery).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                val gson = GsonBuilder().create()
                val movieFeed = gson.fromJson(body, MovieFeed::class.java)

                runOnUiThread{
                    mainAdapter?.movies = movieFeed.data.movies
                    mainAdapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("failed to process request")
            }

        })
    }




    // Search feature in the title bar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem: MenuItem = menu.findItem(R.id.menu_search)
        val searchView: SearchView = searchItem.actionView as SearchView
//        println("im printing something")
//        println(searchView)

//        passwordEditText.doAfterTextChanged{ }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query is String){
                    searchMoviesInYTS(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        return true
    }
}