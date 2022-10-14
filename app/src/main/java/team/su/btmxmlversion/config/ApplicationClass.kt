package team.su.btmxmlversion.config

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.su.btmxmlversion.BuildConfig
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {
    /*private val NURSING_HOME_API_URL = BuildConfig.URL_NURSING_HOME

    companion object {
        lateinit var SignupRetrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()

        initRetrofitInstance()
    }


    private fun initRetrofitInstance() {
        SignupRetrofit = Retrofit.Builder()
            .baseUrl(NURSING_HOME_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/

}