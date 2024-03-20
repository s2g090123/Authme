package com.example.authme.usecase

import com.example.authme.repository.UserRepository
import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.common.UserError
import com.example.githubusersdk.models.UserInfo

class GetUserInfo(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        token: String,
        userName: String
    ): GitHubResponse<UserInfo, UserError> {
        return repository.getUserInfo(token, userName)
    }
}