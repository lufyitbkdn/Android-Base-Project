package com.shapee.android.data.network

import com.shapee.android.model.UploadImageResponse
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AppApi {
    @POST("upload_avatar")
    fun uploadImage(
        @Header("X-API-KEY") accessToken: String,
        @Header("X-User-Key") childId: Long,
        @Header("Content-Type") contentType: String = "image/jpeg",
        @Body imageFileBody: RequestBody
    ): Single<UploadImageResponse>
}