package com.shapee.sample.data.utils

import retrofit2.http.Field
import retrofit2.http.POST

interface ApiClient {
    @POST("/login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String)
}