package com.nimawoods.watchlog.api.movies

import com.nimawoods.watchlog.api.APIService
import com.nimawoods.watchlog.constants.AppConstants.Companion.API_BASE_URL
import com.nimawoods.watchlog.constants.AppConstants.Companion.API_KEY
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MoviesAPIHandler {

    private val apiBaseURL = "$API_BASE_URL/?$API_KEY"

    suspend fun fetchMovie(title: String): String {
        return suspendCoroutine { continuation ->
            val apiURLCall = "$apiBaseURL&t=$title&type=movie"

            APIService.fetch(
                apiURLCall,
                onSuccess = { response -> continuation.resume(response) },
                onError = { error -> continuation.resumeWithException(error) }
            )
        }
    }

    suspend fun searchMovies(query: String): String {
        return suspendCoroutine { continuation ->
            val apiURL = "$apiBaseURL&s=$query&type=movie"
            APIService.fetch(
                apiURL,
                onSuccess = { response -> continuation.resume(response) },
                onError = { error -> continuation.resumeWithException(error) }
            )
        }
    }

}
