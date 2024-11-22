package com.nimawoods.watchlog.api.film

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

class MovieMapper {

    fun parseSearchToMovieList(json: String): List<MovieModel> {
        return try {
            val jsonObject = JSONObject(json)
            val searchArray = jsonObject.optJSONArray("Search") ?: JSONArray()

            val movieList = mutableListOf<MovieModel>()

            for (i in 0 until searchArray.length()) {
                val movieObject = searchArray.optJSONObject(i)
                if (movieObject != null && movieObject.optString("Type") == "movie") {
                    movieList.add(parseMovieObject(movieObject))
                }
            }

            movieList
        } catch (e: Exception) {
            Log.e("MovieMapper", "Error parsing movies: $json", e)
            emptyList()
        }
    }

    private fun parseMovieObject(jsonObject: JSONObject): MovieModel {
        return MovieModel(
            title = jsonObject.optString("Title", "Unknown Title"),
            coverURL = jsonObject.optString("Poster", ""),
            genre = jsonObject.optString("Genre", "Unknown Genre"),
            rating = jsonObject.optString("imdbRating", "No Rating"),
            description = jsonObject.optString("Plot", "No description available"),
            year = jsonObject.optString("Year", "Unknown Date"),
            releaseDate = jsonObject.optString("Year", "Unknown Date")
        )
    }
}
