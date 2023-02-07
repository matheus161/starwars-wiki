package com.example.maratachallenge

import android.os.Parcel
import android.os.Parcelable

/*
* Parceble class: Allows send data between Activities
* */

data class Character (
    val name : String? = null,
    val height : String? = null,
    val mass : String? = null,
    val hair_color : String? = null,
    val skin_color: String? = null,
    val eye_color: String? = null,
    val birth_year: String? = null,
    val gender: String? = null,
    var homeworld: String? = null,
    val specie: String? = null,
    var isFavorite: Boolean? = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(height)
        parcel.writeString(mass)
        parcel.writeString(hair_color)
        parcel.writeString(skin_color)
        parcel.writeString(eye_color)
        parcel.writeString(birth_year)
        parcel.writeString(gender)
        parcel.writeString(homeworld)
        parcel.writeString(specie)
        parcel.writeValue(isFavorite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }

}