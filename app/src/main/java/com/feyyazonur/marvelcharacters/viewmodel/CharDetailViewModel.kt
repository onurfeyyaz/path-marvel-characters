package com.feyyazonur.marvelcharacters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feyyazonur.marvelcharacters.model.Model
import com.feyyazonur.marvelcharacters.repository.CRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharDetailViewModel constructor(private val CRepository: CRepository) : ViewModel() {

    private var _comicsList = MutableLiveData<Model>()
    val comicsList: MutableLiveData<Model>
        get() = _comicsList

    fun getComics(charId: Int) {
        val response = CRepository.getComicsOfChar(charId)
        response.enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                if (response.body() != null) {
                    _comicsList.postValue(response.body())
                }
                // Log.d("CharsListDetail", "char detail response body : : ${response.body()}")
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                // Log.d("CharsListDetail", "char detail retrofit onFailure : : $t")
            }
        })
    }

}