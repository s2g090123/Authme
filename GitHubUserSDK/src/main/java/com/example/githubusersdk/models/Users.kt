package com.example.githubusersdk.models

data class Users(
    val data: List<User>,
    val lastSince: Int,
    val nextSince: Int?,
    val perPage: Int,
)