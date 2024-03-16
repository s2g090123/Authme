package com.example.authme

import com.example.authme.repository.TestUserRepository
import com.example.authme.repository.UserRepository
import com.example.authme.usecase.GetUser
import com.example.authme.usecase.GetUserInfo
import com.example.authme.usecase.GetUserStream
import com.example.authme.usecase.UserUseCase
import org.koin.dsl.module

val testModule = module {
    single { TestUserRepository() as UserRepository }

    // use case
    factory { UserUseCase(get(), get(), get()) }
    factory { GetUser(get()) }
    factory { GetUserInfo(get()) }
    factory { GetUserStream(get()) }
}