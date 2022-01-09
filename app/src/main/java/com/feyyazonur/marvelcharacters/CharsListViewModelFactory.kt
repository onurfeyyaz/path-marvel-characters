package com.feyyazonur.marvelcharacters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharsListViewModelFactory constructor(private val repository: CharsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharsListViewModel::class.java)) {
            CharsListViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found!")
        }
    }

}