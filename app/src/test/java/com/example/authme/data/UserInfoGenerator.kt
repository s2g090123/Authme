package com.example.authme.data

import com.example.githubusersdk.models.UserInfo

object UserInfoGenerator {
    fun create(): List<UserInfo> {
        return listOf(
            UserInfo(
                avatarUrl = "",
                name = "1",
                bio = "",
                login = "1",
                siteAdmin = false,
                location = "",
                blog = "",
            ),
            UserInfo(
                avatarUrl = "",
                name = "2",
                bio = "",
                login = "2",
                siteAdmin = false,
                location = "",
                blog = "",
            ),
            UserInfo(
                avatarUrl = "",
                name = "3",
                bio = "",
                login = "3",
                siteAdmin = false,
                location = "",
                blog = "",
            )
        )
    }
}