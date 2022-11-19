package team.su.btmxmlversion.ui.login.protector

import team.su.btmxmlversion.base.BaseCallback
import team.su.btmxmlversion.models.LoginProtectorResponse

interface ProtectorCallback: BaseCallback {

    fun getProtectorLoginSuccess(response: LoginProtectorResponse)

}