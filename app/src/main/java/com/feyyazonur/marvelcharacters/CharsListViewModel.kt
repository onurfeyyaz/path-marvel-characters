package com.feyyazonur.marvelcharacters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feyyazonur.marvelcharacters.model.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharsListViewModel constructor(private val repository: CharsRepository) : ViewModel() {

    private var _charsList = MutableLiveData<List<Model>>()
    val charsList: MutableLiveData<List<Model>>
        get() = _charsList

    fun getCharacters() {
        val response = repository.getCharacters()
        response.enqueue(object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                _charsList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
            }
        })
    }
}