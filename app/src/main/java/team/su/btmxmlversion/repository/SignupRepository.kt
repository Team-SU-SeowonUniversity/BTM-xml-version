package team.su.btmxmlversion.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.su.btmxmlversion.dto.SignupInfirmRequestBody
import team.su.btmxmlversion.dto.SignupInstitutionBody
import team.su.btmxmlversion.dto.SignupParentBody
import team.su.btmxmlversion.models.CertifiedResultResponse
import team.su.btmxmlversion.models.SignupInfirmResponse
import team.su.btmxmlversion.models.SignupInstitutionResponse
import team.su.btmxmlversion.models.SignupParentResponse
import team.su.btmxmlversion.ui.signup.service.SignupService
import team.su.btmxmlversion.ui.signup.signupinfirm.SignupInfirmCallback
import team.su.btmxmlversion.ui.signup.signupmanager.institution.InstitutionSignupCallback
import team.su.btmxmlversion.ui.signup.signupmanager.parent.ParentSignupCallback

class SignupRepository(
    private val signupService: SignupService,
) {
    fun tryInfirmSignup(
        infirmDTO: SignupInfirmRequestBody,
        signupInfirmCallback: SignupInfirmCallback
    ) {
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

    fun tryCertifiedInstitution(
        facilityName: String,
        representative: String,
        institutionSignupCallback: InstitutionSignupCallback
    ) {
        signupService.certifiedInstitution(facilityName, representative).enqueue(object :
            Callback<CertifiedResultResponse> {
            override fun onResponse(
                call: Call<CertifiedResultResponse>,
                response: Response<CertifiedResultResponse>
            ) {
                val body = requireNotNull(response.body())

                institutionSignupCallback.getInstitution(body)
            }

            override fun onFailure(call: Call<CertifiedResultResponse>, t: Throwable) {
                institutionSignupCallback.getRetrofitException()
            }
        })
    }

    fun tryInstitutionSignup(
        institutionDTO: SignupInstitutionBody,
        institutionSignupCallback: InstitutionSignupCallback
    ) {
        signupService.signupInstitution(institutionDTO).enqueue(object :
            Callback<SignupInstitutionResponse> {
            override fun onResponse(
                call: Call<SignupInstitutionResponse>,
                response: Response<SignupInstitutionResponse>
            ) {
                val body = requireNotNull(response.body())

                institutionSignupCallback.getInstitutionSignupSuccess(body)
            }

            override fun onFailure(call: Call<SignupInstitutionResponse>, t: Throwable) {
                institutionSignupCallback.getRetrofitException()
            }
        })
    }

    fun tryParentSignup(
        parentDTO: SignupParentBody,
        parentSignupCallback: ParentSignupCallback
    ) {
        signupService.signupParent(parentDTO).enqueue(object :
            Callback<SignupParentResponse> {
            override fun onResponse(
                call: Call<SignupParentResponse>,
                response: Response<SignupParentResponse>
            ) {
                val body = requireNotNull(response.body())

                parentSignupCallback.getParentSignupSuccess(body)
            }

            override fun onFailure(call: Call<SignupParentResponse>, t: Throwable) {
                parentSignupCallback.getRetrofitException()
            }

        })
    }

}