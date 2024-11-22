package com.nimawoods.watchlog.api.series

import com.nimawoods.watchlog.api.APIService
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SeriesAPIHandler {

    private val API_BASE_URL = "https://www.omdbapi.com/?apikey=1bdb0a7d"

    suspend fun fetchSeries(
        title: String,
        season: Int? = null,
        episode: Int? = null
    ): String {
        return suspendCoroutine { continuation ->
            val apiURLCall = buildString {
                append("$API_BASE_URL&t=$title")
                append("&type=series")
                season?.let { append("&Season=$it") }
                episode?.let { append("&Episode=$it") }
            }

            APIService.fetch(
                apiURLCall,
                onSuccess = { response -> continuation.resume(response) },
                onError = { error -> continuation.resumeWithException(error) }
            )
        }
    }

    suspend fun searchSeries(query: String): String {
        return suspendCoroutine { continuation ->
            val apiURL = "$API_BASE_URL&s=$query&type=series"
            APIService.fetch(
                apiURL,
                onSuccess = { response -> continuation.resume(response) },
                onError = { error -> continuation.resumeWithException(error) }
            )
        }
    }
}
