package com.example.randompet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private val pokemonURLs = mutableListOf<String>()
    private lateinit var rvPokemon: RecyclerView
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fetch Pokémon URLs
        getPokiImageURLs()

        // Initialize RecyclerView and Adapter
        rvPokemon = findViewById(R.id.rvPokemon)
        adapter = PokemonAdapter(pokemonURLs)
        rvPokemon.adapter = adapter
        rvPokemon.layoutManager = LinearLayoutManager(this)
        rvPokemon.setHasFixedSize(true)
    }

    private fun getPokiImageURLs() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon?limit=20", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Poki", "response successful$json")

                val resultsJSON = json.jsonObject.getJSONArray("results")
                for (i in 0 until resultsJSON.length()) {
                    val pokemonURL = resultsJSON.getJSONObject(i).getString("url")
                    pokemonURLs.add(pokemonURL)
                }

                // Notify the adapter that data has changed
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Poki Error", errorResponse)
            }
        }]
    }
}

