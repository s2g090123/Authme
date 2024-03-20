package com.example.authme.usecase

import com.example.authme.common.InfoError
import com.example.authme.common.Resource
import com.example.authme.repository.UserRepository
import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.common.UserInfoError
import com.example.githubusersdk.models.UserInfo

class GetUserInfo(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        token: String,
        userName: String
    ): Resource<UserInfo, InfoError> {
        return repository
            .getUserInfo(token, userName)
            .toResource()
    }
}

private fun GitHubResponse<UserInfo, UserInfoError>.toResource(): Resource<UserInfo, InfoError> {
    return when (this) {
        is GitHubResponse.Error -> {
            when (error) {
                is UserInfoError.HttpError -> Resource.Error(InfoError.HTTP_ERROR)
                is UserInfoError.NetworkError -> Resource.Error(InfoError.NETWORK_ERROR)
                is UserInfoError.NotFoundError -> Resource.Error(InfoError.NOT_FOUND_ERROR)
                is UserInfoError.UnknownError -> Resource.Error(InfoError.UNKNOWN_ERROR)
            }
        }
        is GitHubResponse.Success -> Resource.Success(data)
    }
}