package team.su.btmxmlversion.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.su.btmxmlversion.dto.InterlockInfoRequestBody
import team.su.btmxmlversion.models.BaseResponse
import team.su.btmxmlversion.models.InterlockInfoResponse
import team.su.btmxmlversion.ui.main.parentMain.infirmInfo.InfirmInfoParentService
import team.su.btmxmlversion.until.AddInfirmCallback

class InterlockRepository(
    private val infirmInfoParentService: InfirmInfoParentService
) {
    fun trySetInterlockInfo(
        interlockInfoDTO: InterlockInfoRequestBody,
        addInfirmCallback: AddInfirmCallback
    ) {
        infirmInfoParentService.interlock(interlockInfoDTO).enqueue(object :
            Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val body = requireNotNull(response.body())

                addInfirmCallback.setInterlock(body)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                addInfirmCallback.getRetrofitException()
            }
        })
    }

    suspend fun tryGetInterlockInfo(email: String): InterlockInfoResponse {
        return infirmInfoParentService.getInterlockInfo(email)
    }

}