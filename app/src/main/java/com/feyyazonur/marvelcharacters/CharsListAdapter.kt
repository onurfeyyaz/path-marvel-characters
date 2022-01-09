package com.feyyazonur.marvelcharacters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feyyazonur.marvelcharacters.databinding.CharPropBinding
import com.feyyazonur.marvelcharacters.model.Model

class CharsListAdapter : RecyclerView.Adapter<CharsListViewHolder>() {

    private var model = mutableListOf<Model>() // Results instead of Model

    fun setCharsList(model: List<Model>) { // Results instead of Models
        this.model = model.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharsListViewHolder {
        val binding =
            CharPropBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharsListViewHolder, position: Int) {
        val char = model[position]
        val charName = char.data

        Log.d("CharsList", charName.toString())
        holder.binding.heroName.text = "charName"

        Glide.with(holder.itemView.context)
            .load("http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/portrait_xlarge.jpg")
            .into(holder.binding.heroImage)

    }

    override fun getItemCount(): Int {
        return model.size
    }
}

class CharsListViewHolder(val binding: CharPropBinding) :
    RecyclerView.ViewHolder(binding.root)