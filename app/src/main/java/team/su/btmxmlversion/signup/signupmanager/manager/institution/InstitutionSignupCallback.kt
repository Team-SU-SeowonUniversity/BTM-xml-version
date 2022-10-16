package team.su.btmxmlversion.signup.signupmanager.manager.institution

import team.su.btmxmlversion.config.BaseCallback
import team.su.btmxmlversion.signup.signupmanager.manager.institution.models.NursingHomeResponse

interface InstitutionSignupCallback: BaseCallback {

    fun getNursingHomeDataSuccess(response: NursingHomeResponse)

    fun getNursingHomeDataFailure()

}
