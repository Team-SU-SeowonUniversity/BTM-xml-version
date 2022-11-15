package team.su.btmxmlversion.ui.login.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import team.su.btmxmlversion.dto.LoginInfirmRequestBody
import team.su.btmxmlversion.models.LoginInfirmResponse

interface LoginService {

    @POST("/login/infirm")
    fun loginInfirm(
        @Body infirmInfo: LoginInfirmRequestBody,
    ) : Call<LoginInfirmResponse>

}