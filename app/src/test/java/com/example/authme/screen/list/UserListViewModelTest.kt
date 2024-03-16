package com.example.authme.screen.list

import androidx.paging.testing.asSnapshot
import com.example.authme.BaseUnitTest
import com.example.authme.data.UserGenerator
import com.example.authme.rule.MainDispatcherRule
import com.example.authme.screen.list.event.ListEvent
import com.example.authme.usecase.UserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.java.KoinJavaComponent.get
import kotlin.test.assertEquals

class UserListViewModelTest : BaseUnitTest() {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setup() {
        viewModel = UserListViewModel(get(UserUseCase::class.java))
    }

    @Test
    fun `getUsers without query`() = runTest {
        val users = viewModel.users.asSnapshot()

        assertEquals(
            expected = UserGenerator.create(),
            actual = users,
        )
    }

    @Test
    fun `getUsers with query`() = runTest {
        viewModel.onEvent(ListEvent.Search("1"))
        delay(500) // debounce

        val users = viewModel.users.asSnapshot()

        assertEquals(
            expected = listOf(UserGenerator.create().first()),
            actual = users,
        )
    }
}