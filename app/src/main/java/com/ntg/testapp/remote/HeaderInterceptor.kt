package com.ntg.testapp.remote

import com.ntg.testapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {

        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", "Client-ID ${BuildConfig.API_KEY}")
                .build()
        )


    }
}