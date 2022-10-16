package team.su.btmxmlversion.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.su.btmxmlversion.BuildConfig
import team.su.btmxmlversion.signup.NursingHomeService
import java.util.concurrent.TimeUnit

object CommonDataServiceLocator {
    private const val BASE_URL = BuildConfig.URL_NURSING_HOME

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

    val nursingHomeService: NursingHomeService = retrofit.create(NursingHomeService::class.java)
}