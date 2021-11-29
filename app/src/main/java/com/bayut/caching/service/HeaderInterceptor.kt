package com.bayut.caching.service

import com.bayut.caching.utility.URLConstants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header(URLConstants.CONTENT_TYPE, URLConstants.APPLICATION_JSON)
            .header(URLConstants.ACCEPT, URLConstants.APPLICATION_JSON)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}