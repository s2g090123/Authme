package com.example.authme.screen.info

import androidx.lifecycle.SavedStateHandle
import com.example.authme.BaseUnitTest
import com.example.authme.common.InfoError
import com.example.authme.data.UserInfoGenerator
import com.example.authme.navigation.Route
import com.example.authme.rule.MainDispatcherRule
import com.example.authme.usecase.UserUseCase
import org.junit.Rule
import org.junit.Test
import org.koin.java.KoinJavaComponent.get
import kotlin.test.assertEquals

class UserInfoViewModelTest : BaseUnitTest() {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `getState with userInfo`() {
        val viewModel = UserInfoViewModel(
            savedStateHandle = SavedStateHandle(mapOf(Route.Info.params to "1")),
            useCase = get(UserUseCase::class.java)
        )

        val actual = viewModel.state
        val expected = MutableInfoState().apply {
            data = UserInfoGenerator.create().first { it.login == "1" }
        }
        assertEquals(
            expected = expected,
            actual = actual
        )
    }

    @Test
    fun `getState with not found`() {
        val viewModel = UserInfoViewModel(
            savedStateHandle = SavedStateHandle(mapOf(Route.Info.params to "4")),
            useCase = get(UserUseCase::class.java)
        )

        val actual = viewModel.state
        val expected = MutableInfoState().apply {
            error = InfoError.NOT_FOUND_ERROR.message
        }
        assertEquals(
            expected = expected,
            actual = actual
        )
    }
}