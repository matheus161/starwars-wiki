package com.example.maratachallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject


class MainActivity : AppCompatActivity(), CharactersClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_characteres)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val items = fetchData()
        val adapter = CharacterListAdapter(items, this)
        recyclerView.adapter = adapter
    }

    private fun fetchData() {
        val url = "https://swapi.dev/api/people"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val characterJsonArray = it.getJSONArray("results")
                val characterArray = ArrayList<Character>()
                for (i in 0 until characterJsonArray.length()) {
                    val charactersJsonObject = characterJsonArray.getJSONObject(i)
                    val character = Character(
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
            },
            Response.ErrorListener {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: String) {
        Toast.makeText(this, "clicked item is $item", Toast.LENGTH_LONG).show()
    }
}