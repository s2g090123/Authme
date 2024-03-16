package com.example.githubusersdk.models

data class Users(
    val data: List<User>,
    val prevPage: Int?,
    val nextPage: Int?,
    val perPage: Int,
)