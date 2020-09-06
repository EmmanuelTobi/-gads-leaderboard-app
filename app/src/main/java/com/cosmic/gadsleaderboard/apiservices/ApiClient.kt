package com.cosmic.gadsleaderboard.apiservices;

import com.cosmic.gadsleaderboard.models.LeaderboardModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {

    private const val BASE_URL = "https://gadsapi.herokuapp.com"

    fun get(): Retrofit {

        val gson = GsonBuilder().setLenient().create()
        val interceptor = HttpLoggingInterceptor()

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

}

interface APIInterface {

    @get:GET("/api/hours")
    val leaderboardByHours: Call<List<LeaderboardModel>?>?

    @get:GET("/api/skilliq")
    val leaderboardBySkillIQ: Call<List<LeaderboardModel>?>?

}
