package com.example.githubusersdk.lib

import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.models.UserInfo
import com.example.githubusersdk.models.Users
import com.example.githubusersdk.utils.assertIsA
import com.example.githubusersdk.utils.assertIsTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GitHubApiManagerTest {
    private val apiKey =
        "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"

    private lateinit var manager: GitHubApiManager

    @Before
    fun setup() {
        manager = GitHubApiManager()
    }

    @Test
    fun `getUsers with default parameter`() = runTest {
        val response = manager.getUsers(apiKey)

        assertIsA<GitHubResponse.Success<Users>>(response)
        val firstUser = response.data?.data?.firstOrNull()
        val lastUser = response.data?.data?.lastOrNull()
        firstUser.assertIsTrue { it?.login == "mojombo" }
        lastUser.assertIsTrue { it?.login == "bmizerany" }
    }

    @Test
    fun `getUsers with default parameter and token contains Bearer`() = runTest {
        val response = manager.getUsers("Bearer $apiKey")

        assertIsA<GitHubResponse.Success<Users>>(response)
        val firstUser = response.data?.data?.firstOrNull()
        val lastUser = response.data?.data?.lastOrNull()
        firstUser.assertIsTrue { it?.login == "mojombo" }
        lastUser.assertIsTrue { it?.login == "bmizerany" }
    }

    @Test
    fun `getUsers with since as 1`() = runTest {
        val response = manager.getUsers(apiKey, since = 1)

        assertIsA<GitHubResponse.Success<Users>>(response)
        val firstUser = response.data?.data?.firstOrNull()
        val lastUser = response.data?.data?.lastOrNull()
        firstUser.assertIsTrue { it?.login == "defunkt" }
        lastUser.assertIsTrue { it?.login == "jnewland" }
    }

    @Test
    fun `getUsers with perPage as 31`() = runTest {
        val response = manager.getUsers(apiKey, perPage = 31)

        assertIsA<GitHubResponse.Success<Users>>(response)
        val firstUser = response.data?.data?.firstOrNull()
        val lastUser = response.data?.data?.lastOrNull()
        firstUser.assertIsTrue { it?.login == "mojombo" }
        lastUser.assertIsTrue { it?.login == "jnewland" }
    }

    @Test
    fun `getUsers with error api key`() = runTest {
        val response = manager.getUsers("")

        assertIsA<GitHubResponse.Error<Users>>(response)
    }

    @Test
    fun `getUserInfo with my account`() = runTest {
        val response = manager.getUserInfo(apiKey, userName = "s2g090123")

        assertIsA<GitHubResponse.Success<UserInfo>>(response)
        response.data
            ?.assertIsTrue { it.login == "s2g090123" }
            ?.assertIsTrue { it.name == "JiaChian" }
    }

    @Test
    fun `getUserInfo with my account and token contains Bearer`() = runTest {
        val response = manager.getUserInfo("Bearer $apiKey", userName = "s2g090123")

        assertIsA<GitHubResponse.Success<UserInfo>>(response)
        response.data
            ?.assertIsTrue { it.login == "s2g090123" }
            ?.assertIsTrue { it.name == "JiaChian" }
    }

    @Test
    fun `getUserInfo with error api key`() = runTest {
        val response = manager.getUserInfo("", userName = "s2g090123")

        assertIsA<GitHubResponse.Error<UserInfo>>(response)
    }

    @Test
    fun `getUserInfo with error user name`() = runTest {
        val response = manager.getUserInfo(apiKey, userName = "")

        assertIsA<GitHubResponse.Error<UserInfo>>(response)
    }
}