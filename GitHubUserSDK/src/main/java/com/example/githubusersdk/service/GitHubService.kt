package com.example.githubusersdk.service

import com.example.githubusersdk.models.UserInfoResponse
import com.example.githubusersdk.models.UserResponse
import com.example.githubusersdk.models.UserSearchResponse
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
    ): Response<List<UserResponse>>

    @GET("search/users")
    suspend fun getUsersByName(
        @Header("Authorization") authorization: String,
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30,
    ): Response<UserSearchResponse>

    @GET("users/{userName}")
    suspend fun getUserInfo(
        @Header("Authorization") authorization: String,
        @Path("userName") userName: String
    ): Response<UserInfoResponse>
}