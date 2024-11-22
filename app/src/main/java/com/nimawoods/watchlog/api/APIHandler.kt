package com.nimawoods.watchlog.api

class APIHandler : AutoCloseable {

    private val API_BASE_URL = "https://www.omdbapi.com/?apikey=1bdb0a7d"

    fun testConnection() {
        APIService.fetch("https://www.omdbapi.com/?apikey=1bdb0a7d&i=tt0910970"
            , onSuccess = { response ->
                println("API CONNECTED! Antwort: $response")
            }
            , onError = { error ->
                println("Fehler: ${error.message}")
            }
        )
    }

    fun searchFilmAndSeries(title: String, onSuccess: (String) -> Unit, onError: (Throwable) -> Unit) {
        val apiURLCall = buildString {
            append(API_BASE_URL)
            append("&s=$title")
        }

        APIService.fetch(
            apiURLCall,
            onSuccess = { response ->
                onSuccess(response)
                println(response)
            },
            onError = { error ->
                onError(error)
            }
        )
    }

    override fun close() {
        println("APIHandler closed")
    }

}