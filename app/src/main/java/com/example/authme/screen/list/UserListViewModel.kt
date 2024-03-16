package com.example.authme.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.authme.screen.list.event.ListEvent
import com.example.authme.usecase.UserUseCase
import com.example.githubusersdk.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class UserListViewModel(
    private val useCase: UserUseCase
) : ViewModel() {
    private val searchImp = MutableStateFlow("")
    val search = searchImp.asStateFlow()

    val users: Flow<PagingData<User>> = search
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest {
            useCase.getUserStream("", "")
        }
        .cachedIn(viewModelScope)

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.Search -> {
                searchImp.value = event.query
            }
        }
    }
}