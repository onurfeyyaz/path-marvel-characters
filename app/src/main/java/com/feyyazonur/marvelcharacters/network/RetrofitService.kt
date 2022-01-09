package com.feyyazonur.marvelcharacters.network

import com.feyyazonur.marvelcharacters.model.Model
import com.feyyazonur.marvelcharacters.model.Results
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// http://gateway.marvel.com/v1/public/comics?ts=1&apikey=4f9b90ceeb2b9ae9f0c067c9a9026447&hash=e8401798d2be4eccc6b1b3918f31811b

private const val BASE_URL = "https://gateway.marvel.com/v1/public/"
private const val TIMESTAMP = "1"
private const val API_KEY = "4f9b90ceeb2b9ae9f0c067c9a9026447"
private const val HASH = "e8401798d2be4eccc6b1b3918f31811b" // md5(timestamp+privatekey+publickey)
private const val AUTH_URL = "ts=$TIMESTAMP&apikey=$API_KEY&hash=$HASH"

interface RetrofitService {

    @GET("characters?$AUTH_URL")
    fun getCharacters(): Call<Model> // Call<Model> ile tüm model geliyor!

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}