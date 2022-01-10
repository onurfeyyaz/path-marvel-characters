package com.feyyazonur.marvelcharacters.network

import com.feyyazonur.marvelcharacters.model.Model
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://gateway.marvel.com/"
private const val TIMESTAMP = 1
private const val API_KEY = "4f9b90ceeb2b9ae9f0c067c9a9026447"
private const val HASH = "e8401798d2be4eccc6b1b3918f31811b" // md5(timestamp+privatekey+publickey)
private const val AUTH_URL = "ts=$TIMESTAMP&apikey=$API_KEY&hash=$HASH"
private const val CHARS_LIMIT = 3
private const val COMICS_LIMIT = 10
private const val DATE_RANGE = "2005-01-01%2C2022-12-12"
private const val ORDER_BY = "onsaleDate"


// about chars with parameters
// https://gateway.marvel.com/v1/public/characters?limit=30&ts=1&apikey=4f9b90ceeb2b9ae9f0c067c9a9026447&hash=e8401798d2be4eccc6b1b3918f31811b

// comics list url with parameters
// https://gateway.marvel.com/v1/public/characters/1011031/comics?dateRange=2005-01-01%2C2022-12-12&orderBy=onsaleDate&limit=10&ts=1&apikey=4f9b90ceeb2b9ae9f0c067c9a9026447&hash=e8401798d2be4eccc6b1b3918f31811b

interface RetrofitService {

    // get 30 chars
    @GET("v1/public/characters?$AUTH_URL")
    fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<Model>

    // get comics of char with characterId, startYear, orderBy, limit,
    // BASE_URL/v1/public/characters/<charId>/comics?dateRange=$DATE_RANGE & orderBy=$ORDER_BY & limit=$COMICS_LIMIT & AUTH_URL
    @GET("v1/public/characters/{charId}/comics?dateRange=$DATE_RANGE&orderBy=$ORDER_BY&limit=$COMICS_LIMIT&$AUTH_URL")
    fun getComicsOfChar(@Path("charId") charId: Int): Call<Model>

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