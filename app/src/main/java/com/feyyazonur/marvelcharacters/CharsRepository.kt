package com.feyyazonur.marvelcharacters

import com.feyyazonur.marvelcharacters.network.RetrofitService

class CharsRepository constructor(private val retrofitService: RetrofitService) {

    fun getCharacters() = retrofitService.getCharacters()

}