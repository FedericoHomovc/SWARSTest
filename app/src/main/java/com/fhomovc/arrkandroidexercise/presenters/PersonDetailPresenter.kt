package com.fhomovc.arrkandroidexercise.presenters

import com.fhomovc.arrkandroidexercise.data.APIManager
import com.fhomovc.arrkandroidexercise.models.Person
import retrofit2.Call
import retrofit2.Callback

class PersonDetailPresenter(private var detailView: PersonDetailView) {

    fun loadPerson(id: String) {
        detailView.showProgress()
        val call = APIManager.loadPerson(id)
        call.enqueue(object : Callback<Person> {
            override fun onResponse(
                call: Call<Person>,
                response: retrofit2.Response<Person>?
            ) {
                detailView.hideProgress()
                if (response != null) {
                    val person: Person = response.body()!!
                    detailView.setData(person)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                detailView.hideProgress()
                detailView.showError()
            }
        })

    }

    interface PersonDetailView {
        fun showProgress()
        fun hideProgress()
        fun setData(person: Person)
        fun showError()
    }
}