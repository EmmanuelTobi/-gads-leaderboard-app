package com.cosmic.gadsleaderboard.models
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName

class LeaderboardHoursModel : ArrayList<LeaderboardHoursModelItem>()

@Keep
data class LeaderboardHoursModelItem(
    @SerializedName("badgeUrl")
    var badgeUrl: String?,
    @SerializedName("country")
    var country: String?,
    @SerializedName("hours")
    var hours: Int?,
    @SerializedName("name")
    var name: String?

)