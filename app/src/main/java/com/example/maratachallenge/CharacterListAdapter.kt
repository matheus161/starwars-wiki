package com.example.maratachallenge

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CharacterListAdapter(): RecyclerView.Adapter<CharacterViewHolder>() {

    private val items: ArrayList<com.example.maratachallenge.Character> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        val viewHolder = CharacterViewHolder(view)
        //view.setOnClickListener{
        //   listener.onItemClicked(items[viewHolder.adapterPosition])
        //}
        return viewHolder
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        var currentItem = items[position]
        holder.nameView.text = currentItem.name
        holder.heightView.text = "height: " + currentItem.height + " cm"
        holder.genderView.text = "gender: " + currentItem.gender
        holder.massView.text = "mass: " + currentItem.mass + " pounds"

        val context = holder.constraintRow.context
        holder.constraintRow.setOnClickListener {
            val intent = Intent(it.context, CharacterDetailActivity::class.java)

            intent.putExtra("character", currentItem)

            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateCharacter(updateCharacter: ArrayList<Character>) {
        items.clear()
        items.addAll(updateCharacter)

        notifyDataSetChanged()
    }
}

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameView: TextView = itemView.findViewById(R.id.name)
    val heightView: TextView = itemView.findViewById(R.id.height)
    val genderView: TextView = itemView.findViewById(R.id.gender)
    val massView: TextView = itemView.findViewById(R.id.mass)
    val constraintRow: ConstraintLayout = itemView.findViewById(R.id.constraint_row)
}

//interface CharactersClicked {
//    fun onItemClicked(item: com.example.maratachallenge.Character)
//}