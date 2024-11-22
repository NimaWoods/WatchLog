package com.nimawoods.watchlog.api.series.model

data class EpisodeModel (
    val title : String,
    val genre : String,
    val rating : String,
    val description : String,
    val shortDescription : String,
    val season : String,
    val episodeNumber: String
)