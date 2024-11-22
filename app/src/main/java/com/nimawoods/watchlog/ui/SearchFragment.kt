package com.nimawoods.watchlog.ui

import ListAdapter
import SeriesMapper
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nimawoods.watchlog.R
import com.nimawoods.watchlog.api.APIHandler
import com.nimawoods.watchlog.api.film.MovieMapper
import com.nimawoods.watchlog.models.ListEntry
import com.nimawoods.watchlog.models.ListItem

class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchInput: EditText
    private lateinit var adapter: ListAdapter
    private lateinit var items: List<ListItem>
    private val debounceHandler = Handler(Looper.getMainLooper())
    private var debounceRunnable: Runnable? = null
    private val debounceDelay = 500L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search_page, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        searchInput = view.findViewById(R.id.custom_search_input)

        items = listOf(
            ListItem(
                title = "Day and Night",
                imageUrl = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/7bAS0ENl7pYogOPVNYOo2o03BuO.jpg",
                year = "2017"),
            ListItem(
                title = "The Mandalorian",
                imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                year = "2019")
        )

        val initialList = listOf(ListEntry.Header("Latest Movies & Series recommendations")) +
                items.map { ListEntry.Item(it) }

        adapter = ListAdapter(initialList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                adapter.updateList(emptyList())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                debounceRunnable?.let { debounceHandler.removeCallbacks(it) }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrBlank()) {
                    adapter.updateList(initialList)
                    return
                }

                debounceRunnable = Runnable {
                    performSearch(s.toString())
                }
                debounceHandler.postDelayed(debounceRunnable!!, debounceDelay)
            }
        })

        return view
    }

    private fun performSearch(query: String) {
        val apiHandler = APIHandler()
        val seriesMapper = SeriesMapper()
        val movieMapper = MovieMapper()

        apiHandler.searchFilmAndSeries(
            query,
            onSuccess = { response ->
                val seriesList = seriesMapper.parseSearchToSeriesList(response).map { it.toListItem() }
                val movieList = movieMapper.parseSearchToMovieList(response).map { it.toListItem() }

                val combinedList = (seriesList + movieList)

                requireActivity().runOnUiThread {
                    if (combinedList.isNotEmpty()) {
                        val updatedList = mutableListOf<ListEntry>().apply {
                            add(ListEntry.Header(getString(R.string.title_search_results, query)))
                            addAll(combinedList.map { ListEntry.Item(it) })
                        }

                        adapter.updateList(updatedList)
                    } else {
                        adapter.updateList(listOf(ListEntry.Header("No results found")))
                    }
                }
            },
            onError = { error ->
                requireActivity().runOnUiThread {
                    adapter.updateList(listOf(ListEntry.Header("Error: ${error.message}")))
                }
            }
        )
    }
}
