package team.su.btmxmlversion.repository

import team.su.btmxmlversion.models.InfirmUserInfoResponse
import team.su.btmxmlversion.ui.main.infirmMain.myPage.service.InfirmUserInfoService

class InfirmUserRepository(
    private val infirmUserInfoService: InfirmUserInfoService
) {
    suspend fun tryGetInfirmUserInfo(
        phoneNumber: String
    ): InfirmUserInfoResponse {
        return infirmUserInfoService.getInfirmUserInfo(phoneNumber)
    }
}