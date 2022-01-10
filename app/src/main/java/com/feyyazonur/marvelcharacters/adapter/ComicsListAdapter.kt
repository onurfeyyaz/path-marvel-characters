package com.feyyazonur.marvelcharacters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feyyazonur.marvelcharacters.databinding.ComicItemBinding
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
            ComicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsListViewHolder, position: Int) {
        val comic = model[position]

        // Log.d("CharsListDetail", "comic title: : ${comic.title}")
        holder.binding.comicTitle.text = comic.title
        holder.binding.comicDescription.text = comic.description

    }

    override fun getItemCount(): Int {
        return model.size
    }
}

class ComicsListViewHolder(val binding: ComicItemBinding) :
    RecyclerView.ViewHolder(binding.root)