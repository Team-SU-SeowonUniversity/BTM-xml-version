package team.su.btmxmlversion.dto

import com.google.gson.annotations.SerializedName

data class InterlockTerminationRequestBody(
    @SerializedName("phoneNumber") val phoneNumber: String
)
