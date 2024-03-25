package com.example.githubusersdk.lib

import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.common.UserInfoError
import com.example.githubusersdk.common.UserListError
import com.example.githubusersdk.models.UserInfo
import com.example.githubusersdk.models.Users

interface ApiManager {
    suspend fun getUsers(
        authorization: String,
        since: Int = 0,
        perPage: Int = 30
    ): GitHubResponse<Users, UserListError>

    suspend fun getUsersByName(
        authorization: String,
        name: String,
        page: Int = 1,
        perPage: Int = 30
    ): GitHubResponse<Users, UserListError>

    suspend fun getUserInfo(
        authorization: String,
        userName: String
    ): GitHubResponse<UserInfo, UserInfoError>
}