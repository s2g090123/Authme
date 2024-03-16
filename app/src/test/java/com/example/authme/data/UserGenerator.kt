package com.example.authme.data

import com.example.githubusersdk.models.User

object UserGenerator {
    fun create(): List<User> {
        return listOf(
            User(
                avatarUrl = "",
                login = "1",
                siteAdmin = false,
            ),
            User(
                avatarUrl = "",
                login = "2",
                siteAdmin = false,
            ),
            User(
                avatarUrl = "",
                login = "3",
                siteAdmin = false,
            )
        )
    }
}