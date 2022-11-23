package team.su.btmxmlversion.dto

import com.google.gson.annotations.SerializedName

data class NameChangeRequestBody(
    @SerializedName("email") val email: String,
    @SerializedName("changeName") val changeName: String
)
