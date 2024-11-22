package com.nimawoods.watchlog.api.movies

import com.nimawoods.watchlog.api.APIService
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MoviesAPIHandler {

    private val API_BASE_URL = "https://www.omdbapi.com/?apikey=1bdb0a7d&plot=short"

    suspend fun fetchMovie(title: String): String {
        return suspendCoroutine { continuation ->
            val apiURLCall = "$API_BASE_URL&t=$title&type=movie"

            APIService.fetch(
                apiURLCall,
                onSuccess = { response -> continuation.resume(response) },
                onError = { error -> continuation.resumeWithException(error) }
            )
        }
    }

    suspend fun searchMovies(query: String): String {
        return suspendCoroutine { continuation ->
            val apiURL = "$API_BASE_URL&s=$query&type=movie"
            APIService.fetch(
                apiURL,
                onSuccess = { response -> continuation.resume(response) },
                onError = { error -> continuation.resumeWithException(error) }
            )
        }
    }

}
