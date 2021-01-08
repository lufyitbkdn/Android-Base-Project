package com.shapee.android.utils

import com.shapee.android.BuildConfig
import java.util.*

object Constants {
    @JvmStatic
    val DEFAULT_LOCALE = Locale("ru", "RU")
    const val BASE_URL = "https://upload.geopapa.com"
    const val API_URL = "api.geopapa.com"
    const val CACHE_SIZE = 10 * 1024 * 1024.toLong()
    const val CONNECT_TIMEOUT: Long = 180 // ms
    const val READ_TIMEOUT: Long = 180 // ms
    const val WRITE_TIMEOUT: Long = 180 // ms
    const val SEND_CODE_TIMEOUT = 60000// ms
    const val MIN_PASSWORD_LENGTH = 8

}
