package com.fhomovc.arrkandroidexercise.models

import java.text.SimpleDateFormat
import java.util.*

data class Person(val name: String, val height: String, val mass: String, val created: String, val url: String) {

    fun formatDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val output = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        val d = sdf.parse(created)
        return output.format(d)
    }

    fun formatHeight(): String {
        return if (height == "unknown") height else (height.toInt() / 100.0).toString()
    }
}
