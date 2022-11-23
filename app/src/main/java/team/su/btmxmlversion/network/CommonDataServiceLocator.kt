package team.su.btmxmlversion.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.su.btmxmlversion.BuildConfig
import team.su.btmxmlversion.ui.login.service.LoginService
import team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis.service.DiagnosisService
import team.su.btmxmlversion.ui.main.infirmMain.myPage.service.InfirmUserInfoService
import team.su.btmxmlversion.ui.main.parentMain.infirmInfo.InfirmInfoParentService
import team.su.btmxmlversion.ui.signup.service.NursingHomeService
import team.su.btmxmlversion.ui.signup.service.SignupService
import java.util.concurrent.TimeUnit


object CommonDataServiceLocator {
    private const val BASE_URL = BuildConfig.URL_NURSING_HOME
    private const val BTM_URL = "http://116.40.6.160:9030"

    private val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(100, TimeUnit.SECONDS)
        .connectTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val BTMRetrofit = Retrofit.Builder()
        .baseUrl(BTM_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val nursingHomeService: NursingHomeService = retrofit.create(NursingHomeService::class.java)
    val loginService: LoginService = BTMRetrofit.create(LoginService::class.java)
    val signupService: SignupService = BTMRetrofit.create(SignupService::class.java)
    val diagnosisService: DiagnosisService = BTMRetrofit.create(DiagnosisService::class.java)
    val infirmUserInfoService: InfirmUserInfoService = BTMRetrofit.create(InfirmUserInfoService::class.java)
    val infirmInfoParentService: InfirmInfoParentService = BTMRetrofit.create(InfirmInfoParentService::class.java)
}