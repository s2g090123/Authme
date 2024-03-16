package com.example.githubusersdk.lib

import com.example.githubusersdk.service.GitHubService
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitBuilder {
    private const val BASE_URL = "https://api.github.com/"

    fun buildGitHubService(): GitHubService {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(GitHubService::class.java)
    }
}