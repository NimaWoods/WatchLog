package com.nimawoods.watchlog.api.series.model

data class SearchResponseModel(
    val Search: List<SeriesModel>,
    val totalResults: Int = 0,
    val Response: String
)
