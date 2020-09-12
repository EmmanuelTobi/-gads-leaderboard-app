package com.cosmic.gadsleaderboard.models
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName

class LeaderboardScoresModel : ArrayList<LeaderboardScoresModelItem>()

@Keep
data class LeaderboardScoresModelItem(
    @SerializedName("badgeUrl")
    var badgeUrl: String?,
    @SerializedName("country")
    var country: String?,
    @SerializedName("score")
    var scores: Int?,
    @SerializedName("name")
    var name: String?

)