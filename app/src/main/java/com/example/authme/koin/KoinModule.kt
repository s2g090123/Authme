package com.example.authme.koin

import com.example.authme.repository.DefaultUserRepository
import com.example.authme.usecase.GetUser
import com.example.authme.usecase.GetUserInfo
import com.example.authme.usecase.UserUseCase
import com.example.githubusersdk.lib.GitHubApiManager
import org.koin.dsl.module

val module = module {
    single { GitHubApiManager() }
    single { DefaultUserRepository(get()) }

    // use case
    factory { UserUseCase(get(), get()) }
    factory { GetUser(get()) }
    factory { GetUserInfo(get()) }
}