package com.fhomovc.arrkandroidexercise.services

import com.fhomovc.arrkandroidexercise.models.PeopleResponseWrapper
import com.fhomovc.arrkandroidexercise.models.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {

    @GET("people/{person_id}")
    fun requestPerson(@Path("person_id") personId: String): Call<Person>

    @GET("people")
    fun requestPeople(@Query("page") page: String): Call<PeopleResponseWrapper>

}