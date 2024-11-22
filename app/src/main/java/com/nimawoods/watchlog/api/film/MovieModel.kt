package com.nimawoods.watchlog.api.film

import com.nimawoods.watchlog.models.ListItem
import com.nimawoods.watchlog.models.WatchlistItem
import java.net.MalformedURLException
import java.net.URL

data class MovieModel(
    val title: String = "Unknown Title",
    val coverURL: String = "",
    val genre: String = "Unknown Genre",
    val rating: String = "No Rating",
    val description: String = "No description available",
    val year: String = "Unknown Year",
    val releaseDate: String = "Unknown Date"
) {
    fun toWatchlistItem(): WatchlistItem {
        return WatchlistItem(
            title = title,
            seasondAndEpisodeString = releaseDate,
            description = description,
            imageURL = resolveCoverUrl()
        )
    }

    fun toListItem(): ListItem {
        return ListItem(
            title = if (title.isNotBlank()) title else "Unknown Title",
            year = if (year.isNotBlank()) year else "No description available",
            imageUrl = resolveCoverUrl()
        )
    }

    private fun resolveCoverUrl(): String {
        return if (isValidUrl(coverURL)) coverURL else "https://ih1.redbubble.net/image.1861329778.2941/st,small,845x845-pad,1000x1000,f8f8f8.jpg"
    }

    private fun isValidUrl(url: String): Boolean {
        return try {
            URL(url)
            true
        } catch (e: MalformedURLException) {
            false
        }
    }
}
