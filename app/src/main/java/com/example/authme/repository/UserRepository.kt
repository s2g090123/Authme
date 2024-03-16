package com.example.authme.repository

import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.models.UserInfo
import com.example.githubusersdk.models.Users

interface UserRepository {
    suspend fun getUsers(token: String, since: Int, perPage: Int): GitHubResponse<Users>

    suspend fun getUsersByName(
        token: String,
        name: String,
        page: Int,
        perPage: Int
    ): GitHubResponse<Users>

    suspend fun getUserInfo(token: String, userName: String): GitHubResponse<UserInfo>
}