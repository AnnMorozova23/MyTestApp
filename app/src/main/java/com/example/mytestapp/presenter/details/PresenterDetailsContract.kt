package com.example.mytestapp.presenter.details

import com.example.mytestapp.presenter.PresenterContract

internal interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
}