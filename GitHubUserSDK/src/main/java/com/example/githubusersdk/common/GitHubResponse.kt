package com.example.githubusersdk.common

typealias RootError = Error

sealed interface GitHubResponse<D, E : RootError> {
    data class Success<D, E : RootError>(val data: D) : GitHubResponse<D, E>
    data class Error<D, E : RootError>(val error: E) : GitHubResponse<D, E>
}