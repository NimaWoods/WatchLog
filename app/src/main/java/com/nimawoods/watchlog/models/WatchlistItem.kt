package com.nimawoods.watchlog.models

data class WatchlistItem(
    val title: String,
    val episode: String,
    val description: String,
    val imageRes: Int,
    val isWatched: Boolean
)