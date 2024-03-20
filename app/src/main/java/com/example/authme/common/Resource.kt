package com.example.authme.common

sealed interface Resource<D, E : CommonError> {
    data class Success<D, E : CommonError>(val data: D) : Resource<D, E>
    data class Error<D, E : CommonError>(val error: E) : Resource<D, E>
}