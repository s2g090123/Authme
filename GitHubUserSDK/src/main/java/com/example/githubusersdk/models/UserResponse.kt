package com.example.githubusersdk.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("site_admin")
    val siteAdmin: Boolean?
)