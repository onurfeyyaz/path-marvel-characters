package com.feyyazonur.marvelcharacters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feyyazonur.marvelcharacters.databinding.CharPropBinding
import com.feyyazonur.marvelcharacters.model.Model
import com.feyyazonur.marvelcharacters.model.Results

class CharsListAdapter : RecyclerView.Adapter<CharsListViewHolder>() {

    private var model = mutableListOf<Results>() // Results instead of Model

    fun setCharsList(model: Model) { // Results instead of Models
        this.model = model.data!!.results!!.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharsListViewHolder {
        val binding =
            CharPropBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharsListViewHolder, position: Int) {
        val char = model[position]

        //Log.d("CharsList", "char name: : ${char.name}")
        holder.binding.heroName.text = char.name

        val imagePath = "${char.thumbnail!!.path}/portrait_small.${char.thumbnail!!.extension}"
        Log.d("CharsList", imagePath)

        Glide.with(holder.itemView.context)
            .load(imagePath) //"http://x.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/portrait_small.jpg")
            .into(holder.binding.heroImage)

        holder.binding.listAdapterCardview.setOnClickListener {
            Log.d("CharsList", "clicked char name: ${char.name}")
            val action =
                CharsListFragmentDirections.actionCharsListFragmentToCharDetailFragment(char)
            holder.binding.listAdapterCardview.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        //Log.d("CharsList", "model size: : : ${model.size}")
        return model.size
    }
}

class CharsListViewHolder(val binding: CharPropBinding) :
    RecyclerView.ViewHolder(binding.root)