package com.cosmic.gadsleaderboard.apiservices

class ApiHelper(private val apiInteface: APIInterface) {

    suspend fun getLeaderBoardByHours() = apiInteface.leaderboardByHours()
    suspend fun getLeaderBoardBySkillIQ() = apiInteface.leaderboardBySkillIQ()
}

class submissionHelper(private val apiInteface: APIInterface, var firstName: String, var lastName: String, var emailAddress: String, var projectLink: String) {

    suspend fun makeSubmission() = apiInteface.submitProject(firstName = firstName, lastName = lastName, emailAddress = emailAddress, projectLink = projectLink);

}
