package team.su.btmxmlversion.signup


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import team.su.btmxmlversion.BuildConfig
import team.su.btmxmlversion.signup.signupmanager.manager.institution.models.NursingHomeResponse

interface NursingHomeService {

    @GET(BuildConfig.ENDPOINT_GET_NURSING_HOME)
    fun getNursingHome(
        @Query("page") page: Int,
        @Query("perPage") perPage:Int,
        @Query("serviceKey") serviceKey:String = BuildConfig.API_KEY
    ) : Call<NursingHomeResponse>

}