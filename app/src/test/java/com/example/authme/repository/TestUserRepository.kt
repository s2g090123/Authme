package com.example.authme.repository

import com.example.authme.data.UserGenerator
import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.models.UserInfo
import com.example.githubusersdk.models.Users

class TestUserRepository : UserRepository {
    private val users = Users(
        data = UserGenerator.create(),
        prevPage = null,
        nextPage = null,
        perPage = 0,
    )

    override suspend fun getUsers(token: String, since: Int, perPage: Int): GitHubResponse<Users> {
        return GitHubResponse.Success(users)
    }

    override suspend fun getUsersByName(
        token: String,
        name: String,
        page: Int,
        perPage: Int
    ): GitHubResponse<Users> {
        return GitHubResponse.Success(
            users.copy(
                data = UserGenerator.create().filter {
                    it.login == name
                }
            )
        )
    }

    override suspend fun getUserInfo(token: String, userName: String): GitHubResponse<UserInfo> {
        TODO("Not yet implemented")
    }
}