package com.feyyazonur.marvelcharacters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feyyazonur.marvelcharacters.repository.CRepository

class CharsListViewModelFactory constructor(private val CRepository: CRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharsListViewModel::class.java)) {
            CharsListViewModel(this.CRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found!")
        }
    }

}