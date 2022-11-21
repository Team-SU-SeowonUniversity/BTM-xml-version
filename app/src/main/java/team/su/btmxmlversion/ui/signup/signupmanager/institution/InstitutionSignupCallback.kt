package team.su.btmxmlversion.ui.signup.signupmanager.institution

import team.su.btmxmlversion.base.BaseCallback
import team.su.btmxmlversion.models.CertifiedResultResponse
import team.su.btmxmlversion.models.NursingHomeResponse
import team.su.btmxmlversion.models.SignupInstitutionResponse

interface InstitutionSignupCallback: BaseCallback {

    fun getNursingHomeDataSuccess(response: NursingHomeResponse)

    fun getCertifiedResult(response: CertifiedResultResponse)

    fun getInstitutionSignupSuccess(response: SignupInstitutionResponse)

}
