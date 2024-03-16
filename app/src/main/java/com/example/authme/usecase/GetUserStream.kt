package com.example.authme.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.authme.paging.UsersPagingSource
import com.example.githubusersdk.models.User
import kotlinx.coroutines.flow.Flow

private const val PAGE_SIZE = 30

class GetUserStream(
    private val getUser: GetUser
) {
    operator fun invoke(
        token: String,
        userName: String = ""
    ): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                UsersPagingSource(getUser, token, userName)
            }
        ).flow
    }
}