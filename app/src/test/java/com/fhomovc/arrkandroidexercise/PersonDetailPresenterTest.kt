package com.fhomovc.arrkandroidexercise

import com.fhomovc.arrkandroidexercise.models.Person
import com.fhomovc.arrkandroidexercise.presenters.PersonDetailPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Response


class PersonDetailPresenterTest {

    private var presenter: PersonDetailPresenter? = null
    private val personDetailView = Mockito.mock(PersonDetailPresenter.PersonDetailView::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(PersonDetailPresenter(personDetailView))
    }

    @Test
    fun handleServerResponse_Success() {
        val person = Mockito.mock(Person::class.java)

        presenter?.onResponse(Response.success(person))
        verify(personDetailView, times(1)).setData(person)
    }

    @Test
    fun handleNetworkError() {
        presenter?.onError()
        verify(personDetailView, times(1)).showError()
    }

}