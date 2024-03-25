package com.example.githubusersdk.lib

import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.common.UserInfoError
import com.example.githubusersdk.common.UserListError
import com.example.githubusersdk.models.UserInfo
import com.example.githubusersdk.models.Users
import com.example.githubusersdk.utils.toUser
import com.example.githubusersdk.utils.toUserInfo
import com.example.githubusersdk.utils.toUsers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class GitHubApiManager : ApiManager {
    private val api by lazy { RetrofitBuilder.buildGitHubService() }

    override suspend fun getUsers(
        authorization: String,
        since: Int,
        perPage: Int
    ): GitHubResponse<Users, UserListError> {
        return try {
            val token = reformatAuthorization(authorization)
            val response = api.getUsers(token, since, perPage)
            if (!response.isSuccessful) {
                return GitHubResponse.Error(
                    UserListError.HttpError(
                        response.code(),
                        response.message()
                    )
                )
            }
            val nextSince = response.retrieveUsersNextSince()
            val data = response.body()?.mapNotNull {
                it.toUser()
            }
            GitHubResponse.Success(
                Users(
                    data = data ?: emptyList(),
                    prevPage = null,
                    nextPage = nextSince,
                    perPage = perPage,
                )
            )
        } catch (e: HttpException) {
            GitHubResponse.Error(
                UserListError.HttpError(
                    e.code(),
                    e.message ?: "A HttpException occurred"
                )
            )
        } catch (e: IOException) {
            GitHubResponse.Error(UserListError.NetworkError("Couldn't reach server. Check your internet connect"))
        } catch (e: Exception) {
            GitHubResponse.Error(UserListError.UnknownError(e.message ?: "Unknown error occurred"))
        }
    }

    override suspend fun getUsersByName(
        authorization: String,
        name: String,
        page: Int,
        perPage: Int
    ): GitHubResponse<Users, UserListError> {
        return try {
            val token = reformatAuthorization(authorization)
            val response = api.getUsersByName(token, name, page, perPage)
            if (!response.isSuccessful) {
                return GitHubResponse.Error(
                    UserListError.HttpError(
                        response.code(),
                        response.message()
                    )
                )
            }
            val nextPage = response.retrieveSearchNextPage()
            val prevPage = response.retrieveSearchPreviousPage()
            val data = response.body()
                ?.toUsers()
                ?.filter { it.login.contains(name) }
                ?: emptyList()
            GitHubResponse.Success(
                Users(
                    data = data,
                    prevPage = prevPage,
                    nextPage = nextPage,
                    perPage = perPage,
                )
            )
        } catch (e: HttpException) {
            GitHubResponse.Error(
                UserListError.HttpError(
                    e.code(),
                    e.message ?: "A HttpException occurred"
                )
            )
        } catch (e: IOException) {
            GitHubResponse.Error(UserListError.NetworkError("Couldn't reach server. Check your internet connect"))
        } catch (e: Exception) {
            GitHubResponse.Error(UserListError.UnknownError(e.message ?: "Unknown error occurred"))
        }
    }

    override suspend fun getUserInfo(
        authorization: String,
        userName: String
    ): GitHubResponse<UserInfo, UserInfoError> {
        return try {
            val token = reformatAuthorization(authorization)
            val response = api.getUserInfo(token, userName)
            if (!response.isSuccessful) {
                return GitHubResponse.Error(
                    UserInfoError.HttpError(
                        response.code(),
                        response.message()
                    )
                )
            }
            response.body()
                ?.toUserInfo()
                ?.let {
                    GitHubResponse.Success(it)
                } ?: GitHubResponse.Error(UserInfoError.NotFoundError("User is not found"))
        } catch (e: HttpException) {
            GitHubResponse.Error(
                UserInfoError.HttpError(
                    e.code(),
                    e.message ?: "A HttpException occurred"
                )
            )
        } catch (e: IOException) {
            GitHubResponse.Error(UserInfoError.NetworkError("Couldn't reach server. Check your internet connect"))
        } catch (e: Exception) {
            GitHubResponse.Error(UserInfoError.UnknownError(e.message ?: "Unknown error occurred"))
        }
    }

    private fun reformatAuthorization(authorization: String): String {
        val token = authorization.trim()
        if (token.startsWith("Bearer ")) return token
        return "Bearer $token"
    }

    private fun Response<*>.retrieveUsersNextSince(): Int? {
        val linkHeader = headers().get("Link") ?: return null
        val hasNextPage = linkHeader.contains("rel=\"next\"")
        return if (hasNextPage) {
            val pattern =
                """.*since=(\d+)&.*>; rel="next"""".toRegex()
            val matchResult = pattern.find(linkHeader)
            return matchResult?.groupValues?.get(1)?.toIntOrNull()
        } else {
            null
        }
    }

    private fun Response<*>.retrieveSearchNextPage(): Int? {
        val linkHeader = headers().get("Link") ?: return null
        val hasNextPage = linkHeader.contains("rel=\"next\"")
        return if (hasNextPage) {
            val pattern =
                """.*page=(\d+)&.*>; rel="next"""".toRegex()
            val matchResult = pattern.find(linkHeader)
            return matchResult?.groupValues?.get(1)?.toIntOrNull()
        } else {
            null
        }
    }

    private fun Response<*>.retrieveSearchPreviousPage(): Int? {
        val linkHeader = headers().get("Link") ?: return null
        val hasNextPage = linkHeader.contains("rel=\"next\"")
        return if (hasNextPage) {
            val pattern =
                """.*page=(\d+)&.*>; rel="prev"""".toRegex()
            val matchResult = pattern.find(linkHeader)
            return matchResult?.groupValues?.get(1)?.toIntOrNull()
        } else {
            null
        }
    }
}