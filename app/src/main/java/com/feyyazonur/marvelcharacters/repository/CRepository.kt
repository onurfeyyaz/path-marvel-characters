package com.feyyazonur.marvelcharacters.repository

import com.feyyazonur.marvelcharacters.network.RetrofitService

class CRepository constructor(private val retrofitService: RetrofitService) {

    fun getCharacters(limit: Int, charOffSet: Int) = retrofitService.getCharacters(limit, charOffSet)
    fun getComicsOfChar(charId: Int) = retrofitService.getComicsOfChar(charId)

}