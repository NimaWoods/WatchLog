package com.nimawoods.watchlog.handler

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.IOException

class APIHandler {

    private val apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MDU0OTQ0NWQ4OGMzNDZiNzg4ZWRlZDhiM2I0ZmVkZCIsIm5iZiI6MTczMjA3MjgyMC44Nzk2NTgsInN1YiI6IjY3M2Q0Zjk3MWMxODY2YWYwNTQ1NWIxMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MC2J2gKfFn6ZqU2z__U1KN4Bbj_L9SUP3zDE9BQ6deI"
    private val baseUrl = "https://api.themoviedb.org/3"
    private val client = OkHttpClient()

    fun fetchPopularMovies(): JsonObject? {
        val url = "$baseUrl/movie/popular?api_key=$apiKey"

        val request = Request.Builder()
            .url(url)
            .build()

        return try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                Gson().fromJson(responseBody, JsonObject::class.java)
            } else {
                println("Error: ${response.code}")
                null
            }
        } catch (e: IOException) {
            println("Exception: ${e.message}")
            null
        }
    }
}
