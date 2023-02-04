package com.example.maratachallenge

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest


class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: CharacterListAdapter
    private var page = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_characteres)

        recyclerView.layoutManager = LinearLayoutManager(this)
        //fetchData()
        mAdapter = CharacterListAdapter()
        recyclerView.adapter = mAdapter

        // Infinite Scroll method
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = recyclerView.childCount
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager)
                    .findFirstVisibleItemPosition()

                if (!isLoading && firstVisibleItem + visibleItemCount >= totalItemCount) {
                    isLoading = true
                    fetchData()
                }
            }
        })
        fetchData()
    }

    private fun fetchData() {
        val url = "https://swapi.dev/api/people?page=$page"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val characterJsonArray = it.getJSONArray("results")
                val characterArray = ArrayList<Character>()
                for (i in 0 until characterJsonArray.length()) {
                    val charactersJsonObject = characterJsonArray.getJSONObject(i)
                    val character = com.example.maratachallenge.Character(
                        charactersJsonObject.getString("name"),
                        charactersJsonObject.getString("height"),
                        charactersJsonObject.getString("mass"),
                        charactersJsonObject.getString("hair_color"),
                        charactersJsonObject.getString("skin_color"),
                        charactersJsonObject.getString("eye_color"),
                        charactersJsonObject.getString("birth_year"),
                        charactersJsonObject.getString("gender"),
                        charactersJsonObject.getString("homeworld"),
                        charactersJsonObject.getString("species"),
                    )
                    characterArray.add(character)
                    page++
                    isLoading=false
                }
                mAdapter.updateCharacter(characterArray)
            },
            Response.ErrorListener {
                isLoading = false
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    //override fun onItemClicked(item: com.example.maratachallenge.Character) {

    //}
}