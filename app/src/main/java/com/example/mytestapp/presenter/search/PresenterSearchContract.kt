package com.example.mytestapp.presenter.search

import com.example.mytestapp.presenter.PresenterContract
import com.example.mytestapp.view.search.ViewSearchContract


internal interface PresenterSearchContract : PresenterContract {
    fun searchGitHub(searchQuery: String)
    fun onAttach(view: ViewSearchContract)
    fun onDetach()
}