package com.cosmic.gadsleaderboard.apiservices;

import com.cosmic.gadsleaderboard.models.LeaderboardHoursModel
import com.cosmic.gadsleaderboard.models.LeaderboardHoursModelItem
import com.cosmic.gadsleaderboard.models.LeaderboardScoresModel
import com.cosmic.gadsleaderboard.models.LeaderboardScoresModelItem
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://gadsapi.herokuapp.com"
private const val SUBMIT_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse"


class ApiClient(baseurl : String) {

    val url = baseurl;

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(url)
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

    @POST("/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    suspend fun submitProject(
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") emailAddress: String,
        @Field("entry.284483984") projectLink: String
    ): Response<Void>

}


