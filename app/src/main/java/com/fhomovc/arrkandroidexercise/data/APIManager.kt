package com.fhomovc.arrkandroidexercise.data

import com.fhomovc.arrkandroidexercise.services.PeopleService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIManager {

    private const val BASE_URL: String = "https://swapi.co/api/"

    private var peopleService: PeopleService

    init {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        peopleService = retrofit.create<PeopleService>(PeopleService::class.java)
    }

    fun loadPeople(page: String) = peopleService.requestPeople(page)

    fun loadPerson(id: String) = peopleService.requestPerson(id)

}