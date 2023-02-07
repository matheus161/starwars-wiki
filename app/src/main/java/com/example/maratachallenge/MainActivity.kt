package com.example.maratachallenge

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: CharacterListAdapter
    val characterArray = ArrayList<Character>()
    var isLoading = false
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //supportActionBar?.hide()
        supportActionBar?.title = "StarWars Characters";
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))

        val recyclerView = findViewById<RecyclerView>(R.id.rv_characteres)
        val searchView = findViewById<SearchView>(R.id.search)
        val fabFavorite = findViewById<FloatingActionButton>(R.id.fab_favorite)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        fetchData()
        mAdapter = CharacterListAdapter()
        recyclerView.adapter = mAdapter

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchList = ArrayList<com.example.maratachallenge.Character>()
                val searchText = newText!!.toLowerCase(Locale.getDefault())

                if (newText != null) {
                    for (i in characterArray) {
                        if (i.name!!.lowercase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()) {
                        Toast.makeText(
                            this@MainActivity,
                            "Nenhum nome correspondente foi encontrado",
                            Toast.LENGTH_LONG
                        ).show()
                        searchList.clear()
                        mAdapter.updateCharacter(searchList)
                    } else {
                        mAdapter.updateCharacter(searchList)
                    }
                }
                return true
            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val totalItems = mAdapter.itemCount

                    if (!isLoading) {
                        if((visibleItemCount + pastVisibleItem) >= totalItems) {
                            page++
                            fetchData()
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }

        })

        fabFavorite.setOnClickListener{
            openFavoriteActivity()
        }
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
                }
                mAdapter.updateCharacter(characterArray)
                isLoading = false
            },
            Response.ErrorListener {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    private fun openFavoriteActivity() {
        val intent = Intent(this, FavoriteCharacterActicity::class.java)
        val favoriteArray = ArrayList<Character>()

        for (i in 0 until characterArray.size) {
            if (characterArray[i].isFavorite == true) {
                favoriteArray.add(characterArray[i])
            }
        }

        intent.putParcelableArrayListExtra("characterArray", favoriteArray)
        startActivity(intent)
    }

    //override fun onItemClicked(item: com.example.maratachallenge.Character) {

    //}
}