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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as MutableInfoState
        if (data != other.data) return false
        if (loading != other.loading) return false
        return error == other.error
    }

    override fun hashCode(): Int {
        var result = data?.hashCode() ?: 0
        result = 31 * result + loading.hashCode()
        result = 31 * result + (error?.hashCode() ?: 0)
        return result
    }
}