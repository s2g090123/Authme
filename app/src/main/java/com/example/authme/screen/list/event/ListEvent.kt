package com.example.authme.screen.list.event

sealed class ListEvent {
    data class Search(val query: String) : ListEvent()
}