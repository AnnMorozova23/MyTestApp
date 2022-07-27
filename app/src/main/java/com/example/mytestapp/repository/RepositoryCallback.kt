package com.example.mytestapp.repository

import com.example.mytestapp.model.SearchResponse
import retrofit2.Response

interface RepositoryCallback {
    fun handleGitHubResponse(response: Response<SearchResponse?>?)
    fun handleGitHubError()

}