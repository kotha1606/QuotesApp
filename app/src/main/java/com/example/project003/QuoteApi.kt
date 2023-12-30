package com.example.project003

import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {

    @GET("random")
    suspend fun randomQuote():Response<List<QuoteModelItem>>
}