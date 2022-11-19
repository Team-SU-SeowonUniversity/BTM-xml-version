package team.su.btmxmlversion.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.su.btmxmlversion.dto.LoginInfirmRequestBody
import team.su.btmxmlversion.dto.LoginProtectorBody
import team.su.btmxmlversion.models.LoginInfirmResponse
import team.su.btmxmlversion.models.LoginProtectorResponse
import team.su.btmxmlversion.ui.login.infirm.InfirmLoginCallback
import team.su.btmxmlversion.ui.login.protector.ProtectorCallback
import team.su.btmxmlversion.ui.login.service.LoginService

class LoginRepository(
    private val loginService: LoginService
) {
    fun tryLoginInfirm(
        loginInfirmDTO: LoginInfirmRequestBody,
        infirmLoginCallback: InfirmLoginCallback
    ) {
        loginService.loginInfirm(loginInfirmDTO).enqueue(object :
            Callback<LoginInfirmResponse> {
            override fun onResponse(
                call: Call<LoginInfirmResponse>,
                response: Response<LoginInfirmResponse>
            ) {
                val body = requireNotNull(response.body())

                infirmLoginCallback.loginInfirmSuccess(body)
            }

            override fun onFailure(call: Call<LoginInfirmResponse>, t: Throwable) {
                infirmLoginCallback.getRetrofitException()
            }
        })
    }

    fun tryLoginProtector(
        loginProtectorDTO: LoginProtectorBody,
        protectorCallback: ProtectorCallback
    ) {
        loginService.loginProtector(loginProtectorDTO).enqueue(object :
            Callback<LoginProtectorResponse> {
            override fun onResponse(
                call: Call<LoginProtectorResponse>,
                response: Response<LoginProtectorResponse>
            ) {
                val body = requireNotNull(response.body())

                protectorCallback.getProtectorLoginSuccess(body)
            }

            override fun onFailure(call: Call<LoginProtectorResponse>, t: Throwable) {
                protectorCallback.getRetrofitException()
            }
        })
    }

}