package com.example.authme.usecase

import com.example.authme.common.ListError
import com.example.authme.common.Resource
import com.example.authme.repository.UserRepository
import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.common.UserListError
import com.example.githubusersdk.models.Users

class GetUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        token: String,
        page: Int,
        perPage: Int = 30,
        userName: String = "",
    ): Resource<Users, ListError> {
        return if (userName.isBlank()) {
            repository
                .getUsers(token, page, perPage)
                .toResource()
        } else {
            repository
                .getUsersByName(token, userName, page, perPage)
                .toResource()
        }
    }
}

private fun GitHubResponse<Users, UserListError>.toResource(): Resource<Users, ListError> {
    return when (this) {
        is GitHubResponse.Error -> {
            when (error) {
                is UserListError.HttpError -> Resource.Error(ListError.HTTP_ERROR)
                is UserListError.NetworkError -> Resource.Error(ListError.NETWORK_ERROR)
                is UserListError.UnknownError -> Resource.Error(ListError.UNKNOWN_ERROR)
            }
        }
        is GitHubResponse.Success -> Resource.Success(data)
    }
}