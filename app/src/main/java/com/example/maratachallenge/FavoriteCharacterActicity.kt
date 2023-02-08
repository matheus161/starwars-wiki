package com.example.maratachallenge

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import java.util.*
import kotlin.collections.ArrayList

class FavoriteCharacterActicity : AppCompatActivity() {

    private lateinit var mAdapter: CharacterListAdapter
    var characterArray = ArrayList<com.example.maratachallenge.Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Favorite StarWars Characters";
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00FFFFFF")))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_characteres)
        val layoutManager = LinearLayoutManager(this)
        val searchView = findViewById<SearchView>(R.id.search)
        characterArray = intent.getParcelableArrayListExtra<com.example.maratachallenge.Character>("characterArray") as ArrayList<Character>


        recyclerView.layoutManager = layoutManager
        mAdapter = CharacterListAdapter()
        recyclerView.adapter = mAdapter
        mAdapter.updateCharacter(characterArray)

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
                            this@FavoriteCharacterActicity,
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

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}