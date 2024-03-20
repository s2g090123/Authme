package com.example.githubusersdk.common

sealed class UserError(val message: String) : Error {
    class HttpError(val code: Int, message: String) : UserError(message)
    class NetworkError(message: String) : UserError(message)
    class UnknownError(message: String) : UserError(message)
    class NotFoundError(message: String) : UserError(message)
}