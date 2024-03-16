package com.example.githubusersdk.utils

import com.example.githubusersdk.models.User
import com.example.githubusersdk.models.UserInfo
import com.example.githubusersdk.models.UserInfoResponse
import com.example.githubusersdk.models.UserResponse
import com.example.githubusersdk.models.UserSearchResponse

fun UserResponse.toUser(): User? {
    login ?: return null
    return User(
        login = login,
        avatarUrl = avatarUrl ?: "",
        siteAdmin = siteAdmin ?: false,
    )
}

fun UserSearchResponse.toUsers(): List<User> {
    return items.mapNotNull {
        it?.toUser()
    }
}

fun UserInfoResponse.toUserInfo(): UserInfo? {
    login ?: return null
    return UserInfo(
        avatarUrl = avatarUrl ?: "",
        name = name ?: "",
        bio = bio ?: "",
        login = login,
        siteAdmin = siteAdmin ?: false,
        location = location ?: "",
        blog = blog ?: "",
    )
}