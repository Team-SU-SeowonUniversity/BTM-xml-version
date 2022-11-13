package team.su.btmxmlversion.ui.signup.signupinfirm

import team.su.btmxmlversion.base.BaseCallback
import team.su.btmxmlversion.models.SignupInfirmResponse

interface SignupInfirmCallback: BaseCallback {

    fun signupInfirmSuccess(response: SignupInfirmResponse)

}