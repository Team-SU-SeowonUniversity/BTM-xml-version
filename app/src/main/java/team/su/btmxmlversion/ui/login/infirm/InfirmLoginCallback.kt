package team.su.btmxmlversion.ui.login.infirm

import team.su.btmxmlversion.base.BaseCallback
import team.su.btmxmlversion.models.LoginInfirmResponse

interface InfirmLoginCallback: BaseCallback {

    fun loginInfirmSuccess(response: LoginInfirmResponse)

}