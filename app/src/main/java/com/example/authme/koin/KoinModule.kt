package com.example.authme.koin

import com.example.authme.repository.DefaultUserRepository
import com.example.authme.repository.UserRepository
import com.example.authme.screen.info.UserInfoViewModel
import com.example.authme.screen.list.UserListViewModel
import com.example.authme.usecase.GetUser
import com.example.authme.usecase.GetUserInfo
import com.example.authme.usecase.GetUserStream
import com.example.authme.usecase.UserUseCase
import com.example.githubusersdk.lib.ApiManager
import com.example.githubusersdk.lib.GitHubApiManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    single { GitHubApiManager() }
    single { DefaultUserRepository(get()) as UserRepository }

    factory { GitHubApiManager() as ApiManager }

    // use case
    factory { UserUseCase(get(), get(), get()) }
    factory { GetUser(get()) }
    factory { GetUserInfo(get()) }
    factory { GetUserStream(get()) }

    viewModel { UserListViewModel(get()) }
    viewModel { UserInfoViewModel(get(), get()) }
}