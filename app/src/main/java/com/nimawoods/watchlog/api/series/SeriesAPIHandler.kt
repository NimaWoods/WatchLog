package com.nimawoods.watchlog.api.series

import com.nimawoods.watchlog.api.APIService
import com.nimawoods.watchlog.constants.AppConstants.Companion.API_BASE_URL
import com.nimawoods.watchlog.constants.AppConstants.Companion.API_KEY
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SeriesAPIHandler {

    private val apiBaseURL = "$API_BASE_URL/?$API_KEY"

    suspend fun fetchSeries(
        title: String,
        season: Int? = null,
        episode: Int? = null
    ): String {
        return suspendCoroutine { continuation ->
            val apiURLCall = buildString {
                append("$apiBaseURL&t=$title")
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
            val apiURL = "$apiBaseURL&s=$query&type=series"
            APIService.fetch(
                apiURL,
                onSuccess = { response -> continuation.resume(response) },
                onError = { error -> continuation.resumeWithException(error) }
            )
        }
    }
}
