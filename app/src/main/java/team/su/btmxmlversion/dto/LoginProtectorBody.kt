package team.su.btmxmlversion.dto

import com.google.gson.annotations.SerializedName

data class LoginProtectorBody(
    @SerializedName("email") val email:String,
    @SerializedName("passWard") val passWard:String,
)
