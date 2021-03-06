package com.example.mytestapp.presenter.details

import com.example.mytestapp.view.details.ViewDetailsContract

internal class DetailsPresenter internal constructor(
    private val viewContract: ViewDetailsContract,
    private var count: Int = 0
) : PresenterDetailsContract {

    private var view: ViewDetailsContract? = null

    override fun onAttach(view: ViewDetailsContract) {
        this.view = view
    }


    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        viewContract.setCount(count)
    }

    override fun onDecrement() {
        count--
        viewContract.setCount(count)
    }

    override fun onDetach() {
        view = null
    }


}
