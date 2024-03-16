package com.example.githubusersdk.common

sealed class GitHubResponse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : GitHubResponse<T>(data = data)
    class Error<T>(message: String) : GitHubResponse<T>(message = message)
}