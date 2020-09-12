package com.cosmic.gadsleaderboard.apiservices;

import com.cosmic.gadsleaderboard.models.LeaderboardHoursModel
import com.cosmic.gadsleaderboard.models.LeaderboardHoursModelItem
import com.cosmic.gadsleaderboard.models.LeaderboardScoresModel
import com.cosmic.gadsleaderboard.models.LeaderboardScoresModelItem
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {

    private const val BASE_URL = "http://gadsapi.herokuapp.com"

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiServiceInterface: APIInterface = getRetrofit().create(APIInterface::class.java)

}

interface APIInterface {

    @GET("/api/hours")
    suspend fun leaderboardByHours(): List<LeaderboardHoursModelItem>

    @GET("/api/skilliq")
    suspend fun leaderboardBySkillIQ(): List<LeaderboardScoresModelItem>

}
