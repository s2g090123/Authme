package com.example.githubusersdk.lib

import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.models.UserInfo
import com.example.githubusersdk.models.Users
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class GitHubApiManager {
    private val api by lazy { RetrofitBuilder.buildGitHubService() }

    suspend fun getUsers(
        authorization: String,
        since: Int = 0,
        perPage: Int = 30
    ): GitHubResponse<Users> {
        return try {
            val token = reformatAuthorization(authorization)
            val response = api.getUsers(token, since, perPage)
            if (!response.isSuccessful) {
                return GitHubResponse.Error(response.message())
            }
            val nextSince = response.retrieveNextSince()
            val data = response.body()?.filter {
                it.login != null
            }
            GitHubResponse.Success(
                Users(
                    data = data ?: emptyList(),
                    lastSince = since,
                    nextSince = nextSince,
                    perPage = perPage,
                )
            )
        } catch (e: HttpException) {
            GitHubResponse.Error(e.localizedMessage ?: "A HttpException occurred")
        } catch (e: IOException) {
            GitHubResponse.Error("Couldn't reach server. Check your internet connect")
        } catch (e: Exception) {
            println(e)
            GitHubResponse.Error("Unknown error occurred")
        }
    }

    suspend fun getUserInfo(
        authorization: String,
        userName: String
    ): GitHubResponse<UserInfo> {
        return try {
            val token = reformatAuthorization(authorization)
            val response = api.getUserInfo(token, userName)
            if (!response.isSuccessful) {
                return GitHubResponse.Error(response.message())
            }
            response.body()?.let {
                GitHubResponse.Success(it)
            } ?: GitHubResponse.Error("User is not found")
        } catch (e: HttpException) {
            GitHubResponse.Error(e.localizedMessage ?: "A HttpException occurred")
        } catch (e: IOException) {
            GitHubResponse.Error("Couldn't reach server. Check your internet connect")
        } catch (e: Exception) {
            GitHubResponse.Error("Unknown error occurred")
        }
    }

    private fun reformatAuthorization(authorization: String): String {
        if (authorization.startsWith("Bearer ")) return authorization
        return "Bearer $authorization"
    }

    private fun Response<*>.retrieveNextSince(): Int? {
        val linkHeader = headers().get("Link") ?: return null
        val hasNextPage = linkHeader.contains("rel=\"next\"")
        return if (hasNextPage) {
            val pattern =
                "<https://api\\.github\\.com/users\\?since=(\\d+)&per_page=\\d+>; rel=\"next\"".toRegex()
            val matchResult = pattern.find(linkHeader)
            return matchResult?.groupValues?.get(1)?.toIntOrNull()
        } else {
            null
        }
    }
}