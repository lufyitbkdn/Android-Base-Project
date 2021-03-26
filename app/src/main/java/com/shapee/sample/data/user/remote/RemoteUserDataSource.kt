package com.shapee.sample.data.user.remote

interface RemoteUserDataSource {
    suspend fun login(username:String, password:String)
}