package team.su.btmxmlversion.ui.signup.service


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import team.su.btmxmlversion.BuildConfig
import team.su.btmxmlversion.models.NursingHomeResponse

interface NursingHomeService {

    @GET(BuildConfig.ENDPOINT_GET_NURSING_HOME)
    fun getNursingHome(
        @Query("page") page: Int = 1,
        @Query("perPage") perPage: Int = 1129,
        @Query("serviceKey") serviceKey:String = BuildConfig.API_KEY
    ) : Call<NursingHomeResponse>

}