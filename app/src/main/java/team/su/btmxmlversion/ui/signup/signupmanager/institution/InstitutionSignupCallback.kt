package team.su.btmxmlversion.ui.signup.signupmanager.institution

import team.su.btmxmlversion.base.BaseCallback
import team.su.btmxmlversion.models.NursingHomeResponse

interface InstitutionSignupCallback: BaseCallback {

    fun getNursingHomeDataSuccess(response: NursingHomeResponse)

    fun getNursingHomeDataFailure()

}
