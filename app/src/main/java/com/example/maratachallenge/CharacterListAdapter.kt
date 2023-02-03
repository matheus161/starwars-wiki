package com.example.maratachallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CharacterListAdapter(private val items: ArrayList<String>, private val listener:CharactersClicked): RecyclerView.Adapter<CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        val viewHolder = CharacterViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        var currentItem = items[position]
        holder.nameView.text = currentItem
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameView: TextView = itemView.findViewById(R.id.name)
}

interface CharactersClicked {
    fun onItemClicked(item: String)
}