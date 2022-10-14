package team.su.btmxmlversion.signup.signupmanager.manager.institution.models

import com.google.gson.annotations.SerializedName

data class DataNursingHome(
    @SerializedName("구분") val sortation: String,
    @SerializedName("대표자") val representative: String,
    @SerializedName("소재지도로명주소") val address: String,
    @SerializedName("시군") val cities: String,
    @SerializedName("시설명") val facilityName: String,
    @SerializedName("전화번호") val phoneNumber: String
)
