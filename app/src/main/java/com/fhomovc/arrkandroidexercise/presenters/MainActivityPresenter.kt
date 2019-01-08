package com.fhomovc.arrkandroidexercise.presenters

import com.fhomovc.arrkandroidexercise.data.APIManager
import com.fhomovc.arrkandroidexercise.models.PeopleResponseWrapper
import com.fhomovc.arrkandroidexercise.models.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityPresenter(private var mainView: MainView) {

    private var pageNumber: Int = 0
    private var lastPage: Boolean = false

    fun loadPeople() {
        if (lastPage) return
        pageNumber++
        mainView.showProgress(pageNumber == 1)
        val call = APIManager.loadPeople(pageNumber.toString())
        call.enqueue(object : Callback<PeopleResponseWrapper> {
            override fun onResponse(
                call: Call<PeopleResponseWrapper>,
                response: retrofit2.Response<PeopleResponseWrapper>?
            ) {
                onResponse(response)
            }

            override fun onFailure(call: Call<PeopleResponseWrapper>, t: Throwable) {
                pageNumber--
                onError()
            }
        })
    }

    fun onResponse(response: Response<PeopleResponseWrapper>?) {
        mainView.hideProgress(pageNumber == 1)
        if (response?.body() != null) {
            val list: List<Person> = response.body()!!.results
            mainView.setData(list)
        } else
            lastPage = true
    }

    fun onError() {
        mainView.hideProgress(pageNumber == 1)
        mainView.showError()
    }

    interface MainView {
        fun showProgress(isFirstPage: Boolean)
        fun hideProgress(isFirstPage: Boolean)
        fun setData(people: List<Person>)
        fun showError()
    }

}