package com.shapee.sample.data.user.remote

import com.shapee.sample.data.utils.ApiClient

class RemoteUserDataSourceImp(private val apiClient: ApiClient) : RemoteUserDataSource {
    override suspend fun login(username: String, password: String) =
        apiClient.login(username, password)
}