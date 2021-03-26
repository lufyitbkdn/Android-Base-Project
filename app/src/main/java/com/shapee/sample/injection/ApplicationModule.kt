package com.shapee.sample.injection

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.shapee.sample.BuildConfig
import com.shapee.sample.data.sharedpref.SharedPreferencesHelper
import com.shapee.sample.data.sharedpref.SharedPreferencesHelperImp
import com.shapee.sample.data.utils.ApiClient
import com.shapee.sample.data.utils.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideApiClient(
        BASE_URL: String,
        loggingInterceptor: HttpLoggingInterceptor
    ): ApiClient = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                // A zero value means no timeout at all.
                // This is enforced because our client isn't able to send/receive a request body/response body always to/from the server within the defined timeout.
                // This guarantees that whatever the size of the image being uploaded is, it won't get `SocketTimeoutException` unless the server itself has issues.
                .writeTimeout(0, TimeUnit.SECONDS)
                .readTimeout(0, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiClient::class.java)


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, AppDatabase.DB_NAME
    )/*.addMigrations(CounterBookDataBase.MIGRATION_1_2, CounterBookDataBase.MIGRATION_2_3)*/
        .build()

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(sharedPreferences: SharedPreferences): SharedPreferencesHelper =
        SharedPreferencesHelperImp(
            sharedPreferences
        )


}