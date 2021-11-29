package com.bayut.caching.di

import com.bayut.caching.component.DashboardRemoteDataSource
import com.bayut.caching.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger module to provide app module functionality.
 */
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @ServerApi
    @Singleton
    @Provides
    fun provideServerApiService(
        @ServerApi okHttpClient: OkHttpClient,
        converterFactory: MoshiConverterFactory
    ) = provideService(
        okHttpClient,
        converterFactory,
        ApiService::class.java,
        baseUrl = ApiService.ENDPOINT
    )


    @Singleton
    @Provides
    fun provideDashboardRemoteDataSource(
        @ServerApi serverApiService: ApiService
    ) =
        DashboardRemoteDataSource(serverApiService)

    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)


    private fun createRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: MoshiConverterFactory,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okHttpClient: OkHttpClient,
        converterFactory: MoshiConverterFactory,
        clazz: Class<T>,
        baseUrl: String
    ): T {
        return createRetrofit(okHttpClient, converterFactory, baseUrl).create(clazz)
    }
}
