package team.su.btmxmlversion.ui.main.parentMain.myPage

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import team.su.btmxmlversion.dto.EmailChangeRequestBody
import team.su.btmxmlversion.dto.NameChangeRequestBody
import team.su.btmxmlversion.models.BaseResponse

interface MyPageParentService {

    @POST("/protector/email-change")
    fun emailChange(
        @Body protectorEmailInfo: EmailChangeRequestBody
    ) : Call<BaseResponse>

    @POST("/protector/name-change")
    fun nameChange(
        @Body protectorNameInfo: NameChangeRequestBody
    ) : Call<BaseResponse>

}