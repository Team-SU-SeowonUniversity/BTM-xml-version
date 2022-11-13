package team.su.btmxmlversion.models

import com.google.gson.annotations.SerializedName

data class NursingHomeResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("perPage") val perPage: Int,
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("currentCount") val currentCount: Int,
    @SerializedName("matchCount") val matchCount: Int,
    @SerializedName("data") val data: List<DataNursingHome>,
)
