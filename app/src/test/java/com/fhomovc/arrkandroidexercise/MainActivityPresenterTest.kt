package com.fhomovc.arrkandroidexercise

import com.fhomovc.arrkandroidexercise.models.PeopleResponseWrapper
import com.fhomovc.arrkandroidexercise.presenters.MainActivityPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Response


class MainActivityPresenterTest {

    private var presenter: MainActivityPresenter? = null
    private val mainView = Mockito.mock(MainActivityPresenter.MainView::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(MainActivityPresenter(mainView))
    }

    @Test
    fun handleServerResponse_Success() {
        val resultList = mock(PeopleResponseWrapper::class.java)

        presenter?.onResponse(Response.success(resultList))
        Mockito.verify(mainView, Mockito.times(1)).setData(resultList.results)
    }

    @Test
    fun handleNetworkError() {
        presenter?.onError()
        Mockito.verify(mainView, Mockito.times(1)).showError()
    }

}
