package team.su.btmxmlversion.ui.main.parentMain.infirmInfo

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import team.su.btmxmlversion.dto.InterlockInfoRequestBody
import team.su.btmxmlversion.models.BaseResponse

interface InfirmInfoParentService {

    @POST("/protector/interlock")
    fun interlock(
        @Body usersInfo: InterlockInfoRequestBody
    ) : Call<BaseResponse>

}