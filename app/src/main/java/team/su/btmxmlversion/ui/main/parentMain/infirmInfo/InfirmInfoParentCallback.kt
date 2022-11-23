package team.su.btmxmlversion.ui.main.parentMain.infirmInfo

import team.su.btmxmlversion.base.BaseCallback
import team.su.btmxmlversion.models.BaseResponse

interface InfirmInfoParentCallback: BaseCallback {

    fun getInterlockTerminationSuccess(response: BaseResponse)

}