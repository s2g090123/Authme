package com.example.authme.data

import com.example.githubusersdk.models.UserInfo

object UserInfoGenerator {
    fun create(): List<UserInfo> {
        return listOf(
            UserInfo(
                name = "1",
                bio = "",
                login = "1",
                siteAdmin = false,
                location = "",
                blog = "",
            ),
            UserInfo(
                name = "2",
                bio = "",
                login = "2",
                siteAdmin = false,
                location = "",
                blog = "",
            ),
            UserInfo(
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