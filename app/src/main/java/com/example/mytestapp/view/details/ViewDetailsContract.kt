package com.example.mytestapp.view.details

import com.example.mytestapp.view.ViewContract

internal interface ViewDetailsContract : ViewContract {
    fun setCount(count: Int)
}