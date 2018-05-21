package tech.ascendio.mvvm.data.api


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.ascendio.mvvm.BuildConfig
import tech.ascendio.mvvm.util.Constants
import java.io.IOException


/**
 * The Imgur API client, which uses the [AppService] Retrofit APIs.
 */
class ImgurApi private constructor() {
    val mImgurService: AppService

    init {
        val client = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor())
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.IMGUR_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mImgurService = retrofit.create(AppService::class.java)
    }

    private class AuthInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val headers = request.headers().newBuilder()
                    .add("Authorization", "Client-ID " + BuildConfig.IMGUR_CLIENT_ID)
                    .build()
            val authenticatedRequest = request.newBuilder().headers(headers).build()
            return chain.proceed(authenticatedRequest)
        }
    }

    companion object {

        private var sInstance: ImgurApi? = null
        private val sLock = Any()

        /**
         * @return an instance of the [ImgurApi] client.
         */
        val instance: ImgurApi
            get() {
                synchronized(sLock) {
                    if (sInstance == null) {
                        sInstance = ImgurApi()
                    }
                }
                return sInstance!!
            }
    }
}