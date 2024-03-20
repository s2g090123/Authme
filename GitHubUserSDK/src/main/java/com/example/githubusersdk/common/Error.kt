package com.example.githubusersdk.common

interface Error

sealed class UserListError(val message: String) : Error {
    class HttpError(val code: Int, message: String) : UserListError(message)
    class NetworkError(message: String) : UserListError(message)
    class UnknownError(message: String) : UserListError(message)
}

sealed class UserInfoError(val message: String) : Error {
    class HttpError(val code: Int, message: String) : UserInfoError(message)
    class NetworkError(message: String) : UserInfoError(message)
    class UnknownError(message: String) : UserInfoError(message)
    class NotFoundError(message: String) : UserInfoError(message)
}