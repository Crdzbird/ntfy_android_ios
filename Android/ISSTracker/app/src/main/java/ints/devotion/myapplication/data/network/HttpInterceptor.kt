package ints.devotion.myapplication.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HttpInterceptor @Inject constructor() : Interceptor {
    companion object ApiConstants {
        const val ISS_NOW = "iss-now.json"
    }

    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestBuilder = request.newBuilder()
        if(request.url.encodedPath.endsWith(".json")) {
            requestBuilder.addHeader("Content-Type", "application/json")
        }
        request = requestBuilder.build()
        return chain.proceed(request)
    }
}