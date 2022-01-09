package com.feyyazonur.marvelcharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feyyazonur.marvelcharacters.databinding.CharPropBinding
import com.feyyazonur.marvelcharacters.model.Model

class CharsListAdapter : RecyclerView.Adapter<CharsListViewHolder>() {

    var model = mutableListOf<Model>() // Results instead of Model

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
        val a = char.copyright

        holder.binding.heroName.text = a

        
    }

    override fun getItemCount(): Int {
        return model.size
    }
}

class CharsListViewHolder(val binding: CharPropBinding) :
    RecyclerView.ViewHolder(binding.root)