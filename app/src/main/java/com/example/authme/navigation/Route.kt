package com.example.authme.navigation

sealed class Route(val path: String, val params: String = "") {
    data object List : Route("list") {
        fun build(): String = path
    }

    data object Info : Route("info", "login") {
        fun build(login: String): String = "$path/$login"
    }

    val route: String = if (params.isEmpty()) path else "$path/{$params}"
}