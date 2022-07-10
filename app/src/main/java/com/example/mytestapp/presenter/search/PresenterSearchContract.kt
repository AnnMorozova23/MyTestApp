package com.example.mytestapp.presenter.search

import com.example.mytestapp.presenter.PresenterContract


internal interface PresenterSearchContract : PresenterContract {
    fun searchGitHub(searchQuery: String)
}