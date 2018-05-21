/*
 * Copyright (C) 2018 Marian Vasilca from Ascendio TechVision
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.ascendio.mvvm.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.ascendio.mvvm.BuildConfig
import tech.ascendio.mvvm.util.Constants
import tech.ascendio.mvvm.util.LiveDataCallAdapterFactory
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
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
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