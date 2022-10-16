package team.su.btmxmlversion.signup

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.su.btmxmlversion.signup.signupmanager.manager.institution.InstitutionSignupCallback
import team.su.btmxmlversion.signup.signupmanager.manager.institution.models.NursingHomeResponse

class SignupRepository(
    private val nursingHomeService: NursingHomeService,
    private val institutionSignupCallback: InstitutionSignupCallback,
) {
    fun tryGetNursingHome() {
        nursingHomeService.getNursingHome(1,1129).enqueue(object : Callback<NursingHomeResponse> {

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