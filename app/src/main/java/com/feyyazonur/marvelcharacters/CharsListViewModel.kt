package com.feyyazonur.marvelcharacters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feyyazonur.marvelcharacters.model.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharsListViewModel constructor(private val repository: CharsRepository) : ViewModel() {

    private var _charsList = MutableLiveData<Model>()
    val charsList: MutableLiveData<Model>
        get() = _charsList

    fun getCharacters() {
        val response = repository.getCharacters()
        response.enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                Log.d("CharsList", "onResponse")
                if (response.body() != null) {
                    _charsList.postValue(response.body())
                }
                Log.d("CharsList", "response body : : ${response.body()}")
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                Log.d("CharsList", "Retrofit onFailure : : $t")
            }
        })
    }
}