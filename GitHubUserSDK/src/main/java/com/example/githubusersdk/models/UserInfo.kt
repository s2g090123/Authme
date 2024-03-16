package com.example.githubusersdk.models

data class UserInfo(
    val name: String,
    val bio: String,
    val login: String,
    val siteAdmin: Boolean,
    val location: String,
    val blog: String
)