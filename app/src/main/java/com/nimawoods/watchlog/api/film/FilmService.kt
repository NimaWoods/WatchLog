package com.nimawoods.watchlog.api.film

class FilmService(private val apiHandler: FilmAPIHandler) {

    fun getMovieDetails(movieId: Int, apiKey: String): String? {
        val url = "https://api.themoviedb.org/3/movie/$movieId?api_key=$apiKey"
        return apiHandler.fetchMovieDetails(url)
    }
}
