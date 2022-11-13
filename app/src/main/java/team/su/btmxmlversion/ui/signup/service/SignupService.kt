package team.su.btmxmlversion.ui.signup.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import team.su.btmxmlversion.dto.SignupInfirmRequestBody
import team.su.btmxmlversion.models.SignupInfirmResponse

interface SignupService {

    @POST("/signup/infirm")
    fun signupInfirm(
        @Body infirmInfo: SignupInfirmRequestBody,
    ) : Call<SignupInfirmResponse>

}