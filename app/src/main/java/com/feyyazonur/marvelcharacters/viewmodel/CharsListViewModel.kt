package com.feyyazonur.marvelcharacters.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feyyazonur.marvelcharacters.model.Model
import com.feyyazonur.marvelcharacters.repository.CRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharsListViewModel constructor(private val CRepository: CRepository) : ViewModel() {

    private var _charsList = MutableLiveData<Model>()
    val charsList: MutableLiveData<Model>
        get() = _charsList

    private var _limit: Int = 30
    val limit: Int
        get() = _limit

    private var _charOffSet: Int = 0
    val charOffSet: Int
        get() = _charOffSet

    fun getCharacters(limit: Int, charOffSet: Int) {
        val response = CRepository.getCharacters(limit, charOffSet)
        response.enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                // Log.d("CharsList", "onResponse")
                if (response.body() != null) {
                    _charsList.postValue(response.body())
                }
                // Log.d("CharsList", "response body : : ${response.body()}")
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                Log.d("CharsList", "Retrofit onFailure : : $t")
            }
        })
    }

    fun limitUp() {
        _charOffSet += 30
        getCharacters(_limit, _charOffSet)
    }
}