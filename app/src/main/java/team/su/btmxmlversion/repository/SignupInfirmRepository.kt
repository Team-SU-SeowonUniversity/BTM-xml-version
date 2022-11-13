package team.su.btmxmlversion.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.su.btmxmlversion.dto.SignupInfirmRequestBody
import team.su.btmxmlversion.models.SignupInfirmResponse
import team.su.btmxmlversion.ui.signup.service.SignupService
import team.su.btmxmlversion.ui.signup.signupinfirm.SignupInfirmCallback

class SignupInfirmRepository(
    private val signupService: SignupService,
) {
    fun tryInfirmSignup(infirmDTO: SignupInfirmRequestBody, signupInfirmCallback: SignupInfirmCallback) {
        signupService.signupInfirm(infirmDTO).enqueue(object :
            Callback<SignupInfirmResponse> {
            override fun onResponse(
                call: Call<SignupInfirmResponse>,
                response: Response<SignupInfirmResponse>
            ) {
                val body = requireNotNull(response.body())

                signupInfirmCallback.signupInfirmSuccess(body)
            }

            override fun onFailure(call: Call<SignupInfirmResponse>, t: Throwable) {
                signupInfirmCallback.getRetrofitException()
            }
        })
    }


}