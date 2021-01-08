package com.shapee.android.di.module

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shapee.android.data.network.AppApi
import com.shapee.android.data.preference.SharedPreferenceHelper
import com.shapee.android.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
internal object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-ddTHH:mm:ss.SSS'Z'").create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.cache(Cache(application.cacheDir, Constants.CACHE_SIZE))
        builder.connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        builder.readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
        builder.writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)
        builder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()

                val requestBuilder = request.newBuilder()
                SharedPreferenceHelper.getInstance(application).accessToken?.let {
                    requestBuilder.addHeader("Authorization", it)
                }
                return chain.proceed(requestBuilder.build())
            }
        })
        return builder.build()
    }

    @Provides
    fun provideApiClient(retrofit: Retrofit): AppApi = retrofit.create(AppApi::class.java)
}