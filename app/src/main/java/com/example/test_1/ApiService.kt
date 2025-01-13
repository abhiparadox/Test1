package com.example.test_1

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getData(): Call<List<Users>>
}

    object ApiClient {
        var retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val api: ApiService by lazy {
            retrofitBuilder.create(ApiService::class.java)
        }
    }

