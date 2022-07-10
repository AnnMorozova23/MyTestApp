package com.example.mytestapp.repository

import com.example.mytestapp.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

internal interface GitHubApi {

    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/repositories")
    fun searchGithub(@Query("q") term: String?): Call<SearchResponse?>?
}