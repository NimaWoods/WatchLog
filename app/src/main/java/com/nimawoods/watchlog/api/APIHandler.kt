package com.nimawoods.watchlog.api

import com.nimawoods.watchlog.constants.AppConstants.Companion.API_BASE_URL
import com.nimawoods.watchlog.constants.AppConstants.Companion.API_KEY

class APIHandler : AutoCloseable {

    private val apiBaseURL = "$API_BASE_URL/?$API_KEY"

    fun testConnection() {
        APIService.fetch(apiBaseURL,
            onSuccess = { response ->
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