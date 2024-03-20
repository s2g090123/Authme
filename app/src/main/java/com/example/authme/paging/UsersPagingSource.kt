package com.example.authme.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.authme.usecase.GetUser
import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.common.UserError
import com.example.githubusersdk.models.User

private const val START_INDEX = 0
private const val START_INDEX_WITH_NAME = 1

class UsersPagingSource(
    private val getUser: GetUser,
    private val token: String,
    private val userName: String = ""
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position =
            params.key ?: if (userName.isNotBlank()) START_INDEX_WITH_NAME else START_INDEX
        return try {
            when (val response = getUser(token, position, params.loadSize, userName)) {
                is GitHubResponse.Error -> {
                    when (response.error) {
                        is UserError.HttpError,
                        is UserError.NetworkError,
                        is UserError.NotFoundError,
                        is UserError.UnknownError -> LoadResult.Error(Exception(response.error.message))
                    }
                }
                is GitHubResponse.Success -> {
                    val items = response.data.data
                    LoadResult.Page(
                        data = items,
                        prevKey = response.data.prevPage,
                        nextKey = response.data.nextPage
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}