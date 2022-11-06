package team.su.btmxmlversion.signup.signupmanager.institution

import team.su.btmxmlversion.config.BaseCallback
import team.su.btmxmlversion.signup.signupmanager.institution.data.NursingHomeResponse

interface InstitutionSignupCallback: BaseCallback {

    fun getNursingHomeDataSuccess(response: NursingHomeResponse)

    fun getNursingHomeDataFailure()

}
