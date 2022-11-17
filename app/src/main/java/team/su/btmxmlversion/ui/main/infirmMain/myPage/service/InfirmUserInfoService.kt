package team.su.btmxmlversion.ui.main.infirmMain.myPage.service

import retrofit2.http.GET
import retrofit2.http.Query
import team.su.btmxmlversion.models.InfirmUserInfoResponse

interface InfirmUserInfoService {

    @GET("/infirm/search")
    suspend fun getInfirmUserInfo(
        @Query("phoneNumber") phoneNumber: String
    ): InfirmUserInfoResponse

}