package com.example.mytestapp.view.search

import com.example.mytestapp.model.SearchResult
import com.example.mytestapp.view.ViewContract

internal interface ViewSearchContract : ViewContract {
    fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    )


    fun displayError()
    fun displayError(error: String)
    fun displayLoading(show: Boolean)
}