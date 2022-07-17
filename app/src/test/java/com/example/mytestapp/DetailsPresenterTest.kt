package com.example.mytestapp

import com.example.mytestapp.presenter.details.DetailsPresenter
import com.example.mytestapp.presenter.details.PresenterDetailsContract
import com.example.mytestapp.view.details.ViewDetailsContract
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter
    var count = 5

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Mock
    private var view: ViewDetailsContract? = null

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)


        presenter = DetailsPresenter(viewContract, count)
    }

    @Test
    fun onAttach_Test() {

        view?.let { presenter.onAttach(it) }
        Assert.assertNotNull(view)

    }

    @Test
    fun setCount_Test() {

        presenter.setCounter(count)
        Assert.assertEquals(count, 5)
    }

    @Test
    fun onIncrement_Test() {
        presenter.onIncrement()
        Mockito.verify(viewContract, Mockito.times(1)).setCount(count = 6)


    }

    @Test
    fun onDecrement_Test() {
        presenter.onDecrement()
        Mockito.verify(viewContract, Mockito.times(1)).setCount(count = 4)


    }

    @Test
    fun onDetach_Test() {
        presenter.onDetach()
       //??????????
    }


}