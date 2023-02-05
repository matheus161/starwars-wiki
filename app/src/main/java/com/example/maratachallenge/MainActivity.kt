package com.example.maratachallenge

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest


class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: CharacterListAdapter
    val characterArray = ArrayList<Character>()
    var isLoading = false
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_characteres)
        val progressBar = findViewById<ProgressBar>(R.id.progress)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        fetchData()
        mAdapter = CharacterListAdapter()
        recyclerView.adapter = mAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount: Int = layoutManager.childCount
                val pasVisibleItem: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
                val totalItems = mAdapter.itemCount

                if(!isLoading) {
                    if(visibleItemCount + pasVisibleItem >= totalItems) {
                        fetchData()
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun fetchData() {
        isLoading = true
        val url = "https://swapi.dev/api/people?page=$page"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val characterJsonArray = it.getJSONArray("results")
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
                    mAdapter.notifyDataSetChanged()
                }
                mAdapter.updateCharacter(characterArray)
                isLoading = false
                page++
            },
            Response.ErrorListener {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    //override fun onItemClicked(item: com.example.maratachallenge.Character) {

    //}
}