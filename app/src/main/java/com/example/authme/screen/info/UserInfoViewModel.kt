package com.example.authme.screen.info

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val stateImp = mutableStateOf(InfoState(loading = true))
    val state: State<InfoState> = stateImp

    init {
        viewModelScope.launch {
            when (val response = useCase.getUserInfo(API_KEY, login)) {
                is Resource.Error -> {
                    stateImp.value = InfoState(error = response.error.message)
                }
                is Resource.Success -> {
                    stateImp.value = InfoState(data = response.data)
                }
            }
        }
    }
}