package com.example.githubusersdk.models


import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("name")
    val name: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("site_admin")
    val siteAdmin: Boolean?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("blog")
    val blog: String?
)