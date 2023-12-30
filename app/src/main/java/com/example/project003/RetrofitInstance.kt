package com.example.project003

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val baseurl="https://zenquotes.io/api/"

    private fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val quoteapi :QuoteApi = getInstance().create(QuoteApi::class.java)
}