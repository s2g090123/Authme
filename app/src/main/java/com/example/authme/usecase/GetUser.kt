package com.example.authme.usecase

import com.example.authme.repository.UserRepository
import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.common.UserError
import com.example.githubusersdk.models.Users

class GetUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        token: String,
        page: Int,
        perPage: Int = 30,
        userName: String = "",
    ): GitHubResponse<Users, UserError> {
        return if (userName.isBlank()) {
            repository.getUsers(token, page, perPage)
        } else {
            repository.getUsersByName(token, userName, page, perPage)
        }
    }
}