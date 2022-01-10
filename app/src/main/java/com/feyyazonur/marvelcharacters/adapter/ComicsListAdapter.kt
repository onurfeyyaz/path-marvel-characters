package com.feyyazonur.marvelcharacters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feyyazonur.marvelcharacters.databinding.ComicsItemBinding
import com.feyyazonur.marvelcharacters.model.Model
import com.feyyazonur.marvelcharacters.model.Results

class ComicsListAdapter : RecyclerView.Adapter<ComicsListViewHolder>() {

    private var model = mutableListOf<Results>()

    fun setComicsList(model: Model) {
        this.model = model.data!!.results!!.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsListViewHolder {
        val binding =
            ComicsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsListViewHolder, position: Int) {
        val comics = model[position]

        // Log.d("CharsListDetail", "comics title: : ${comics.title}")
        holder.binding.comicsTitle.text = comics.title
        holder.binding.comicsDescription.text = comics.description

    }

    override fun getItemCount(): Int {
        return model.size
    }
}

class ComicsListViewHolder(val binding: ComicsItemBinding) :
    RecyclerView.ViewHolder(binding.root)