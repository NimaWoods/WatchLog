package com.nimawoods.watchlog.models

data class WatchlistItem(
    val title: String,
    val seasondAndEpisodeString: String,
    val description: String,
    val imageURL: String,
    val isWatched: Boolean = false
)