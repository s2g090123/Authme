package com.example.authme.usecase

data class UserUseCase(
    val getUser: GetUser,
    val getUserInfo: GetUserInfo,
    val getUserStream: GetUserStream,
)