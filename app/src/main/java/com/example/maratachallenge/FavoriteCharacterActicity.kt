package com.example.maratachallenge

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class FavoriteCharacterActicity : AppCompatActivity() {

    private lateinit var mAdapter: CharacterListAdapter
    var characterArray = ArrayList<com.example.maratachallenge.Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Favorite StarWars Characters";
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))

        val recyclerView = findViewById<RecyclerView>(R.id.rv_characteres)
        val layoutManager = LinearLayoutManager(this)
        characterArray = intent.getParcelableArrayListExtra<com.example.maratachallenge.Character>("characterArray") as ArrayList<Character>


        recyclerView.layoutManager = layoutManager
        mAdapter = CharacterListAdapter()
        recyclerView.adapter = mAdapter
        mAdapter.updateCharacter(characterArray)

    }
}