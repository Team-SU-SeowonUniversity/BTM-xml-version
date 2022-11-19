package team.su.btmxmlversion.ui.signup.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import team.su.btmxmlversion.dto.SignupInfirmRequestBody
import team.su.btmxmlversion.models.CertifiedResultResponse
import team.su.btmxmlversion.models.SignupInfirmResponse

interface SignupService {

    @POST("/signup/infirm")
    fun signupInfirm(
        @Body infirmInfo: SignupInfirmRequestBody,
    ) : Call<SignupInfirmResponse>

    @GET("/certified-institution")
    fun certifiedInstitution(
        @Query("facilityName") facilityName: String,
        @Query("representative") representative: String
    ) : Call<CertifiedResultResponse>

}