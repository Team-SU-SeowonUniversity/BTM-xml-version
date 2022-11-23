package team.su.btmxmlversion.ui.main.parentMain.infirmInfo

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import team.su.btmxmlversion.dto.InterlockInfoRequestBody
import team.su.btmxmlversion.dto.InterlockTerminationRequestBody
import team.su.btmxmlversion.models.BaseResponse
import team.su.btmxmlversion.models.InterlockInfoResponse

interface InfirmInfoParentService {

    @POST("/protector/interlock")
    fun interlock(
        @Body usersInfo: InterlockInfoRequestBody
    ) : Call<BaseResponse>

    @GET("/protector/interlock-info")
    suspend fun getInterlockInfo(
        @Query("email") email: String
    ) : InterlockInfoResponse

    @POST("/protector/interlock-termination")
    fun interlockTermination(
        @Body interlockTerminationInfo: InterlockTerminationRequestBody
    ) : Call<BaseResponse>

}