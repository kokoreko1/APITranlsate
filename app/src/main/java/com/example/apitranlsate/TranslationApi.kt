package com.example.apitranlsate

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface TranslationApi {

        @GET("/translate/yoda.json")
        fun translateToYoda(@Body request: TranslationRequest): Call<TranslationResponse>

        @GET("/translate/morse.json")
        fun translateToMorse(@Body request: TranslationRequest): Call<TranslationResponse>

}
