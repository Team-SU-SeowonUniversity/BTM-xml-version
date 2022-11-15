package team.su.btmxmlversion.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.su.btmxmlversion.models.NursingHomeResponse
import team.su.btmxmlversion.ui.signup.service.NursingHomeService
import team.su.btmxmlversion.ui.signup.signupmanager.institution.InstitutionSignupCallback

class NursingHomeRepository(
    private val nursingHomeService: NursingHomeService,
) {
    fun tryGetNursingHome(institutionSignupCallback: InstitutionSignupCallback) {
        nursingHomeService.getNursingHome().enqueue(object : Callback<NursingHomeResponse> {

            override fun onResponse(
                call: Call<NursingHomeResponse>,
                response: Response<NursingHomeResponse>
            ) {
                val body = requireNotNull(response.body())

                if (response.isSuccessful) {
                    institutionSignupCallback.getNursingHomeDataSuccess(body)
                } else {
                    institutionSignupCallback.getNursingHomeDataFailure()
                }

            }

            override fun onFailure(call: Call<NursingHomeResponse>, t: Throwable) {
                institutionSignupCallback.getRetrofitException()
            }
        })
    }
}