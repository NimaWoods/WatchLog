package com.nimawoods.watchlog.api.series.model

import com.nimawoods.watchlog.R
import com.nimawoods.watchlog.models.ListItem
import com.nimawoods.watchlog.models.WatchlistItem
import java.net.MalformedURLException
import java.net.URL

data class SeriesModel(
    val title: String = "Unknown Title",
    val coverURL: String = "https://ih1.redbubble.net/image.1861329778.2941/st,small,845x845-pad,1000x1000,f8f8f8.jpg",
    val genre: String = "Unknown Genre",
    val rating: String = "No Rating",
    val description: String = "No description available",
    val year: String = "Unknown Year",
    val shortDescription: String = "",
    val episodes: List<EpisodeModel> = emptyList(),
    val currentEpisodeIdentifier: EpisodeIdentifier? = null
) {

    fun toWatchlistItem(): WatchlistItem {
        return WatchlistItem(
            title = title,
            seasondAndEpisodeString = currentEpisodeIdentifier?.let {
                getEpisode(it.season, it.episode)
            } ?: "N/A",
            description = shortDescription,
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

    fun getEpisode(season: Int, episode: Int): String {
        val currentEpisodeModel = episodes.find {
            it.season == season.toString() && it.episodeNumber == episode.toString()
        }

        val seasonLocal = R.string.episode_season.toString();
        val episodeLocal = R.string.episode_number.toString();

        return currentEpisodeModel?.let {
            "$seasonLocal ${it.season} | $episodeLocal ${it.episodeNumber}"
        } ?: "$seasonLocal $season | $episodeLocal $episode"
    }

    private fun resolveCoverUrl(): String {
        return if (isValidUrl(coverURL)) coverURL else ""
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
