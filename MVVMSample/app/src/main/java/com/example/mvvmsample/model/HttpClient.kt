package com.example.mvvmsample.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object HttpClient {
    fun buildService(): QiitaService = Retrofit.Builder()
        .baseUrl("https://qiita.com")
        .client(OkHttpClient.Builder().build()).also {
            it.addConverterFactory(JacksonConverterFactory.create())
        }
        .build()
        .create(QiitaService::class.java)
}
