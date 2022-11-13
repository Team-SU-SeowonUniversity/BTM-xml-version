package team.su.btmxmlversion.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.su.btmxmlversion.BuildConfig
import team.su.btmxmlversion.ui.signup.service.NursingHomeService
import team.su.btmxmlversion.ui.signup.service.SignupService
import java.util.concurrent.TimeUnit

object CommonDataServiceLocator {
    private const val BASE_URL = BuildConfig.URL_NURSING_HOME
    private const val BTM_URL = "http://10.0.2.2:9030"

    private val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .connectTimeout(5000, TimeUnit.MILLISECONDS)
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
    val signupService: SignupService = BTMRetrofit.create(SignupService::class.java)
}