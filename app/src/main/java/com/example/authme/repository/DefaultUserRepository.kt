package com.example.authme.repository

import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.lib.GitHubApiManager
import com.example.githubusersdk.models.UserInfo
import com.example.githubusersdk.models.Users

class DefaultUserRepository(
    private val manager: GitHubApiManager,
) : UserRepository {
    override suspend fun getUsers(token: String, since: Int, perPage: Int): GitHubResponse<Users> {
        return manager.getUsers(token, since, perPage)
    }

    override suspend fun getUsersByName(
        token: String,
        name: String,
        page: Int,
        perPage: Int
    ): GitHubResponse<Users> {
        return manager.getUsersByName(token, name, page, perPage)
    }

    override suspend fun getUserInfo(
        token: String,
        userName: String
    ): GitHubResponse<UserInfo> {
        return manager.getUserInfo(token, userName)
    }
}