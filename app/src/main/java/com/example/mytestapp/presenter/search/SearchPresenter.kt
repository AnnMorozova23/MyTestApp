package com.example.mytestapp.presenter.search

import com.example.mytestapp.model.SearchResponse
import com.example.mytestapp.repository.GitHubRepository
import com.example.mytestapp.view.ViewContract
import com.example.mytestapp.view.search.ViewSearchContract
import retrofit2.Response
import com.example.mytestapp.repository.GitHubRepository.GitHubRepositoryCallback

internal class SearchPresenter internal constructor(
    private val viewContract: ViewSearchContract,
    private val repository: GitHubRepository
) : PresenterSearchContract, GitHubRepositoryCallback {

    private var view: ViewContract? = null
    private var isSuccess: Boolean = false



    override fun searchGitHub(searchQuery: String) {
        viewContract.displayLoading(true)
        repository.searchGithub(searchQuery, this)
    }


    override fun handleGitHubResponse(response: Response<SearchResponse?>?) {
        viewContract.displayLoading(false)
        if (response != null && response.isSuccessful) {
            val searchResponse = response.body()
            val searchResults = searchResponse?.searchResults
            val totalCount = searchResponse?.totalCount
            if (searchResults != null && totalCount != null) {
                viewContract.displaySearchResults(
                    searchResults,
                    totalCount
                )
            } else {
                viewContract.displayError("Search results or total count are null")
            }
        } else {
            viewContract.displayError("Response is null or unsuccessful")
        }
    }

    override fun handleGitHubError() {
        viewContract.displayLoading(false)
        viewContract.displayError()
    }

}
