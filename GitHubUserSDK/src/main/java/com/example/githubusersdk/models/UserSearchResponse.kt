package com.example.githubusersdk.models

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(
    @SerializedName("items") val items: List<UserResponse?>
)