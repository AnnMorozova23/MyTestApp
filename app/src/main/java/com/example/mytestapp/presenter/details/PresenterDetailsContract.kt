package com.example.mytestapp.presenter.details

import com.example.mytestapp.presenter.PresenterContract
import com.example.mytestapp.view.details.ViewDetailsContract

internal interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
    fun onAttach(view:ViewDetailsContract)
    fun onDetach()
}