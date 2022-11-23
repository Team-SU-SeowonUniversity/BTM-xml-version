package team.su.btmxmlversion.until

import team.su.btmxmlversion.base.BaseCallback
import team.su.btmxmlversion.models.BaseResponse

interface ChangeCallback: BaseCallback {

    fun getChangeSuccess(response: BaseResponse)

}