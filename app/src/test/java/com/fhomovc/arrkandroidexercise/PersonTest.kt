package com.fhomovc.arrkandroidexercise

import com.fhomovc.arrkandroidexercise.models.Person
import org.junit.Assert.assertEquals
import org.junit.Test

class PersonTest {

    @Test
    fun dateFormatTest() {
        val person = Person("name", "175", "70", "2014-12-09T13:50:51.644000Z", "https://swapi.co/api/people/1/")
        assertEquals("09-12-2014 13:50:51", person.formatDate())
    }

    @Test
    fun heightFormatTest() {
        var person = Person("name", "175", "70", "2014-12-09T13:50:51.644000Z", "https://swapi.co/api/people/1/")
        assertEquals("1.75", person.formatHeight())
        person = Person("name", "unknown", "70", "2014-12-09T13:50:51.644000Z", "https://swapi.co/api/people/1/")
        assertEquals("unknown", person.formatHeight())
    }
}