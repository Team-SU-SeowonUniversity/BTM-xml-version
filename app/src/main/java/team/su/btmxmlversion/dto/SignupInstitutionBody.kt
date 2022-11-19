package team.su.btmxmlversion.dto

import com.google.gson.annotations.SerializedName

data class SignupInstitutionBody(
    @SerializedName("email") val email: String,
    @SerializedName("passWard") val passWard: String,
    @SerializedName("facilityName") val facilityName: String,
    @SerializedName("representative") val representative: String,
)
