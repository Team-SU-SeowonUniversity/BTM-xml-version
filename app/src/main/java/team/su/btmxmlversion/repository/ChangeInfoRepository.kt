package team.su.btmxmlversion.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.su.btmxmlversion.dto.EmailChangeRequestBody
import team.su.btmxmlversion.dto.NameChangeRequestBody
import team.su.btmxmlversion.models.BaseResponse
import team.su.btmxmlversion.ui.main.parentMain.myPage.MyPageParentService
import team.su.btmxmlversion.until.ChangeCallback

class ChangeInfoRepository(
    private val myPageParentService: MyPageParentService
) {
    fun tryEmailChange(
        protectorInfoDTO: EmailChangeRequestBody,
        changeCallback: ChangeCallback
    ) {
        myPageParentService.emailChange(protectorInfoDTO).enqueue(object :
            Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val body = requireNotNull(response.body())

                changeCallback.getChangeSuccess(body)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                changeCallback.getRetrofitException()
            }
        })
    }

    fun tryNameChange(
        protectorInfoDTO: NameChangeRequestBody,
        changeCallback: ChangeCallback
    ) {
        myPageParentService.nameChange(protectorInfoDTO).enqueue(object :
            Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val body = requireNotNull(response.body())

                changeCallback.getChangeSuccess(body)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                changeCallback.getRetrofitException()
            }
        })
    }
}