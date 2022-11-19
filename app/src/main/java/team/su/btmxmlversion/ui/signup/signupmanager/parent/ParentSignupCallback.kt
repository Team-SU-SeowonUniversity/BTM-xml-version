package team.su.btmxmlversion.ui.signup.signupmanager.parent

import team.su.btmxmlversion.base.BaseCallback
import team.su.btmxmlversion.models.SignupParentResponse

interface ParentSignupCallback: BaseCallback {

    fun getParentSignupSuccess(response: SignupParentResponse)

}