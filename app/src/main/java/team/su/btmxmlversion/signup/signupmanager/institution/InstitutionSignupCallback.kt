package team.su.btmxmlversion.signup.signupmanager.institution

import team.su.btmxmlversion.base.BaseCallback
import team.su.btmxmlversion.signup.signupmanager.institution.data.NursingHomeResponse

interface InstitutionSignupCallback: BaseCallback {

    fun getNursingHomeDataSuccess(response: NursingHomeResponse)

    fun getNursingHomeDataFailure()

}
