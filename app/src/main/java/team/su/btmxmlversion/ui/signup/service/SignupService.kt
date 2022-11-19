package team.su.btmxmlversion.ui.signup.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import team.su.btmxmlversion.dto.SignupInfirmRequestBody
import team.su.btmxmlversion.dto.SignupInstitutionBody
import team.su.btmxmlversion.models.CertifiedResultResponse
import team.su.btmxmlversion.models.SignupInfirmResponse
import team.su.btmxmlversion.models.SignupInstitutionResponse

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

    @POST("/signup/manager/institution")
    fun signupInstitution(
        @Body institutionInfo: SignupInstitutionBody
    ) : Call<SignupInstitutionResponse>

}