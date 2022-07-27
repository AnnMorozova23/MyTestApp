package com.example.mytestapp.repository

import com.example.mytestapp.model.SearchResponse
import retrofit2.Response

internal class FakeGitHubRepository : RepositoryContract {
    override fun searchGithub(
        query: String,
        callback: RepositoryCallback
    ) {
        callback.handleGitHubResponse(Response.success(SearchResponse(42, listOf())))
    }
}