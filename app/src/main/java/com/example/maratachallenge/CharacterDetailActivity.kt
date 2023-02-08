package com.example.maratachallenge

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class CharacterDetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_detail)
        supportActionBar?.title = "Character Detail"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00FFFFFF")))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val character = intent.getParcelableExtra<com.example.maratachallenge.Character>("character")
        val urlHomeWorld = character!!.homeworld
        val urlSpecie = character!!.specie
        if(character != null) {
            val name : TextView = findViewById(R.id.textViewFullNameValue)
            val height : TextView = findViewById(R.id.textViewHeightValue)
            val mass : TextView = findViewById(R.id.textViewMassValue)
            val hairColor : TextView = findViewById(R.id.textViewHairColorValue)
            val skinColor : TextView = findViewById(R.id.textViewSkinColorValue)
            val eyeColor : TextView = findViewById(R.id.textViewEyeColorValue)
            val birthYear : TextView = findViewById(R.id.textViewBirthYearValue)
            val gender : TextView = findViewById(R.id.textViewGenderValue)
            val homeWorld : TextView = findViewById(R.id.textViewHomeWorldValue)

            // Requisição da Home World
            val jsonObjectRequestHomeWorld = JsonObjectRequest(
                Request.Method.GET,
                urlHomeWorld,
                null,
                Response.Listener { response ->
                    homeWorld.text = response.getString("name")
                },
                Response.ErrorListener {

                }

            )
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequestHomeWorld)

            name.text = character.name
            height.text = character.height
            mass.text = character.mass
            hairColor.text = character.hair_color
            skinColor.text = character.skin_color
            eyeColor.text = character.eye_color
            birthYear.text = character.birth_year
            gender.text = character.gender
            //specie.text = character.specie
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}