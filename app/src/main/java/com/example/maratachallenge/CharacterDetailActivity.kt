package com.example.maratachallenge

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CharacterDetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_detail)

        val character = intent.getParcelableExtra<com.example.maratachallenge.Character>("character")
        if(character != null) {
            val name : TextView = findViewById(R.id.detailActivityName)
            val height : TextView = findViewById(R.id.detailActivityHeight)
            val mass : TextView = findViewById(R.id.detailActivityMass)
            val hairColor : TextView = findViewById(R.id.detailActivityHairColor)
            val skinColor : TextView = findViewById(R.id.detailActivitySkinColor)
            val eyeColor : TextView = findViewById(R.id.detailActivityEyeColor)
            val birthYear : TextView = findViewById(R.id.detailActivityBirthYear)
            val gender : TextView = findViewById(R.id.detailActivityGender)
            val homeWorld : TextView = findViewById(R.id.detailActivityHomeWorld)
            val specie : TextView = findViewById(R.id.detailActivitySpecie)

            name.text = character.name
            height.text = character.height
            mass.text = character.mass
            hairColor.text = character.hair_color
            skinColor.text = character.skin_color
            eyeColor.text = character.eye_color
            birthYear.text = character.birth_year
            gender.text = character.gender
            homeWorld.text = character.homeworld
            specie.text = character.specie
        }
    }
}