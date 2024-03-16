package com.example.authme.screen.info

import com.example.githubusersdk.models.UserInfo

data class InfoState(
    val data: UserInfo? = null,
    val loading: Boolean = false,
    val error: String? = null
)