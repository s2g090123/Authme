package com.example.authme.screen.info

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.githubusersdk.models.UserInfo

@Stable
interface InfoState {
    val data: UserInfo?
    val loading: Boolean
    val error: String?
}

class MutableInfoState : InfoState {
    override var data: UserInfo? by mutableStateOf(null)
    override var loading: Boolean by mutableStateOf(false)
    override var error: String? by mutableStateOf(null)
}