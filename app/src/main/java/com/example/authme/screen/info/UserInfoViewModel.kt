package com.example.authme.screen.info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authme.API_KEY
import com.example.authme.common.Resource
import com.example.authme.navigation.Route
import com.example.authme.usecase.UserUseCase
import kotlinx.coroutines.launch

class UserInfoViewModel(
    savedStateHandle: SavedStateHandle,
    useCase: UserUseCase
) : ViewModel() {
    private val login: String =
        savedStateHandle[Route.Info.params] ?: throw Exception("login is null")

    private val stateImp = MutableInfoState()
    val state: InfoState = stateImp

    init {
        viewModelScope.launch {
            stateImp.loading = true
            when (val response = useCase.getUserInfo(API_KEY, login)) {
                is Resource.Error -> {
                    stateImp.error = response.error.message
                    stateImp.loading = false
                }
                is Resource.Success -> {
                    stateImp.data = response.data
                    stateImp.loading = false
                }
            }
        }
    }
}