package com.example.githubusersdk.service

import com.example.githubusersdk.models.User
import com.example.githubusersdk.models.UserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GitHubService {
    @GET("users")
    suspend fun getUsers(
        @Header("Authorization") authorization: String,
        @Query("since") since: Int = 0,
        @Query("per_page") perPage: Int = 30,
    ): Response<List<User>>

    @GET("users/{userName}")
    suspend fun getUserInfo(
        @Header("Authorization") authorization: String,
        @Path("userName") userName: String
    ): Response<UserInfo>
}