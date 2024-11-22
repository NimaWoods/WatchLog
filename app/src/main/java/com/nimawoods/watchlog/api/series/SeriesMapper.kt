import android.util.Log
import com.nimawoods.watchlog.R
import com.nimawoods.watchlog.api.series.model.SeriesModel
import org.json.JSONArray
import org.json.JSONObject

class SeriesMapper {

    fun parseSearchToSeriesList(json: String): List<SeriesModel> {
        return try {
            val jsonObject = JSONObject(json)
            val searchArray = jsonObject.optJSONArray("Search") ?: JSONArray()

            val seriesList = mutableListOf<SeriesModel>()

            for (i in 0 until searchArray.length()) {
                val seriesObject = searchArray.optJSONObject(i)
                if (seriesObject != null && seriesObject.optString("Type") == "series") {
                    seriesList.add(parseSeriesObject(seriesObject))
                }
            }

            seriesList
        } catch (e: Exception) {
            Log.e("SeriesMapper", "Error parsing series list: $json", e)
            emptyList()
        }
    }


    private fun parseSeriesArray(jsonArray: JSONArray): List<SeriesModel> {
        val seriesList = mutableListOf<SeriesModel>()

        for (i in 0 until jsonArray.length()) {
            val seriesObject = jsonArray.optJSONObject(i)
            seriesObject?.let {
                seriesList.add(parseSeriesObject(it))
            }
        }

        return seriesList
    }

    fun parseSeriesObject(jsonObject: JSONObject): SeriesModel {
        return SeriesModel(
            title = jsonObject.optString("Title", R.string.unknown_title.toString()),
            coverURL = jsonObject.optString("Poster", ""),
            genre = jsonObject.optString("Genre", R.string.unknown_genre.toString()),
            rating = jsonObject.optString("imdbRating", R.string.unknown_rating.toString()),
            description = jsonObject.optString("Plot", R.string.unknown_description.toString()),
            year = jsonObject.optString("Year", R.string.unknown_year.toString()),
            shortDescription = jsonObject.optString("Plot", R.string.unknown_description.toString()),
            episodes = emptyList(),
            currentEpisodeIdentifier = null
        )
    }
}
