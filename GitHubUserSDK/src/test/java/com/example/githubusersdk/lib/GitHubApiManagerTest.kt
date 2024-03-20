package com.example.githubusersdk.lib

import com.example.githubusersdk.BuildConfig
import com.example.githubusersdk.common.GitHubResponse
import com.example.githubusersdk.common.UserError
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
    // TODO - Replace with your api key
    private val apiKey = BuildConfig.API_KEY

    private lateinit var manager: GitHubApiManager

    @Before
    fun setup() {
        manager = GitHubApiManager()
    }

    @Test
    fun `getUsers with default parameter`() = runTest {
        val response = manager.getUsers(apiKey)

        val (firstUser, lastUser) = response
            .assertIsA<GitHubResponse.Success<Users, UserError>>()
            .data
            .data
            .let { it.firstOrNull() to it.lastOrNull() }
        firstUser.assertIsTrue { it?.login == "mojombo" }
        lastUser.assertIsTrue { it?.login == "bmizerany" }
    }

    @Test
    fun `getUsers with default parameter and token contains Bearer`() = runTest {
        val response = manager.getUsers("Bearer $apiKey")

        val (firstUser, lastUser) = response
            .assertIsA<GitHubResponse.Success<Users, UserError>>()
            .data
            .data
            .let { it.firstOrNull() to it.lastOrNull() }
        firstUser.assertIsTrue { it?.login == "mojombo" }
        lastUser.assertIsTrue { it?.login == "bmizerany" }
    }

    @Test
    fun `getUsers with since as 1`() = runTest {
        val response = manager.getUsers(apiKey, since = 1)

        val (firstUser, lastUser) = response
            .assertIsA<GitHubResponse.Success<Users, UserError>>()
            .data
            .data
            .let { it.firstOrNull() to it.lastOrNull() }
        firstUser.assertIsTrue { it?.login == "defunkt" }
        lastUser.assertIsTrue { it?.login == "jnewland" }
    }

    @Test
    fun `getUsers with perPage as 31`() = runTest {
        val response = manager.getUsers(apiKey, perPage = 31)

        val (firstUser, lastUser) = response
            .assertIsA<GitHubResponse.Success<Users, UserError>>()
            .data
            .data
            .let { it.firstOrNull() to it.lastOrNull() }
        firstUser.assertIsTrue { it?.login == "mojombo" }
        lastUser.assertIsTrue { it?.login == "jnewland" }
    }

    @Test
    fun `getUsers with error api key`() = runTest {
        val response = manager.getUsers("")

        response
            .assertIsA<GitHubResponse.Error<Users, UserError.HttpError>>()
    }

    @Test
    fun `getUsersByName with my account`() = runTest {
        val response = manager.getUsersByName(apiKey, "s2g090123")

        response
            .assertIsA<GitHubResponse.Success<Users, UserError>>()
            .data
            .data
            .firstOrNull()
            .assertIsTrue { it?.login == "s2g090123" }
    }

    @Test
    fun `getUsersByName with error name`() = runTest {
        val response = manager.getUsersByName(apiKey, "")

        response
            .assertIsA<GitHubResponse.Error<Users, UserError.HttpError>>()
    }

    @Test
    fun `getUsersByName with not found`() = runTest {
        val response = manager.getUsersByName(apiKey, "jfgdkluqio")

        response
            .assertIsA<GitHubResponse.Success<Users, UserError>>()
            .data
            .data
            .assertIsTrue { it.isEmpty() }
    }

    @Test
    fun `getUserInfo with my account`() = runTest {
        val response = manager.getUserInfo(apiKey, userName = "s2g090123")

        response
            .assertIsA<GitHubResponse.Success<UserInfo, UserError>>()
            .data
            .assertIsTrue { it.login == "s2g090123" }
            .assertIsTrue { it.name == "JiaChian" }
    }

    @Test
    fun `getUserInfo with my account and token contains Bearer`() = runTest {
        val response = manager.getUserInfo("Bearer $apiKey", userName = "s2g090123")

        response
            .assertIsA<GitHubResponse.Success<UserInfo, UserError>>()
            .data
            .assertIsTrue { it.login == "s2g090123" }
            .assertIsTrue { it.name == "JiaChian" }
    }

    @Test
    fun `getUserInfo with error api key`() = runTest {
        val response = manager.getUserInfo("", userName = "s2g090123")

        response
            .assertIsA<GitHubResponse.Error<UserInfo, UserError.HttpError>>()
    }

    @Test
    fun `getUserInfo with error user name`() = runTest {
        val response = manager.getUserInfo(apiKey, userName = "")

        response
            .assertIsA<GitHubResponse.Error<UserInfo, UserError.HttpError>>()
    }
}