package com.cosmic.gadsleaderboard.apiservices

class ApiHelper(private val apiInteface: APIInterface) {

    suspend fun getLeaderBoardByHours() = apiInteface.leaderboardByHours
    suspend fun getLeaderBoardBySkillIQ() = apiInteface.leaderboardBySkillIQ

}