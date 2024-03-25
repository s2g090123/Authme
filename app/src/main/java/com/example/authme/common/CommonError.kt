package com.example.authme.common

interface CommonError

enum class ListError(val message: String) : CommonError {
    HTTP_ERROR("A HttpException occurred"),
    NETWORK_ERROR("Couldn't reach server. Check your internet connect"),
    UNKNOWN_ERROR("Unknown error occurred")
}

enum class InfoError(val message: String) : CommonError {
    HTTP_ERROR("A HttpException occurred"),
    NETWORK_ERROR("Couldn't reach server. Check your internet connect"),
    UNKNOWN_ERROR("Unknown error occurred"),
    NOT_FOUND_ERROR("User is not found")
}