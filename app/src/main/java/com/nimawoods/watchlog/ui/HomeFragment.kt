package com.nimawoods.watchlog.ui

import SeriesMapper
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nimawoods.watchlog.R
import com.nimawoods.watchlog.adapter.CalendarAdapter
import com.nimawoods.watchlog.adapter.WatchlistAdapter
import com.nimawoods.watchlog.api.series.SeriesAPIHandler
import com.nimawoods.watchlog.models.CalendarItem
import com.nimawoods.watchlog.models.WatchlistItem
import kotlinx.coroutines.launch
import org.json.JSONObject

class HomeFragment : Fragment() {

    private lateinit var btnFilme: Button
    private lateinit var btnSerien: Button
    private lateinit var watchlistAdapter: WatchlistAdapter
    private var isSerieSelected = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        btnFilme = view.findViewById(R.id.btn_filme)
        btnSerien = view.findViewById(R.id.btn_serien)

        setupCalendarRecyclerView(view)
        setupWatchlistRecyclerView(view)

        lifecycleScope.launch {
            val watchlistItems = getWatchlistItemsSerien()
            updateWatchlist(watchlistItems)
        }

        updateButtonStyles()

        btnFilme.setOnClickListener {
            if (isSerieSelected) {
                isSerieSelected = false
                updateButtonStyles()
                updateWatchlist(getWatchlistItemsFilme())
            }
        }

        btnSerien.setOnClickListener {
            if (!isSerieSelected) {
                isSerieSelected = true
                updateButtonStyles()

                lifecycleScope.launch {
                    val watchlistItems = getWatchlistItemsSerien()
                    updateWatchlist(watchlistItems)
                }
            }
        }

        return view
    }

    private fun setupCalendarRecyclerView(view: View) {
        val calendarItems = listOf(
            CalendarItem("Mo", "23"),
            CalendarItem("Di", "24"),
            CalendarItem("Mi", "25"),
            CalendarItem("Do", "26"),
            CalendarItem("Fr", "27")
        )

        val calendarRecyclerView = view.findViewById<RecyclerView>(R.id.calendar_recycler)
        calendarRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        calendarRecyclerView.adapter = CalendarAdapter(calendarItems) { selectedItem ->
            Log.d("HomeFragment", "Selected: ${selectedItem.date}")
        }
        calendarRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(requireContext()))
    }

    private fun setupWatchlistRecyclerView(view: View) {
        val watchlistRecyclerView = view.findViewById<RecyclerView>(R.id.watchlist_recycler)
        watchlistAdapter = WatchlistAdapter(emptyList()) { item ->
            Log.d("HomeFragment", "Clicked: ${item.title}")
        }
        watchlistRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        watchlistRecyclerView.adapter = watchlistAdapter
    }

    private fun updateButtonStyles() {
        if (!isSerieSelected) {
            btnFilme.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.purple))
            btnSerien.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.front))
        } else {
            btnSerien.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.purple))
            btnFilme.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.front))
        }
    }

    private fun updateWatchlist(newItems: List<WatchlistItem>) {
        watchlistAdapter.updateList(newItems)
    }

    private fun getWatchlistItemsFilme(): List<WatchlistItem> {
        return listOf(

        )
    }

    private suspend fun getWatchlistItemsSerien(): List<WatchlistItem> {
        val seriesApiHandler = SeriesAPIHandler()
        val seriesMapper = SeriesMapper()

        val seriesNames = listOf("Breaking Bad", "Stranger Things")
        val watchlistItems = mutableListOf<WatchlistItem>()

        for (seriesName in seriesNames) {
            try {
                val responseJson = seriesApiHandler.fetchSeries(seriesName, 1, 1)
                val seriesModel = seriesMapper.parseSeriesObject(JSONObject(responseJson))
                watchlistItems.add(seriesModel.toWatchlistItem())
            } catch (e: Exception) {
                Log.e("getWatchlistItemsSerien", "Error fetching/parsing series for $seriesName", e)
            }
        }

        return watchlistItems
    }

    private class HorizontalSpaceItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
        private val spaceWidth: Int = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 0f, context.resources.displayMetrics
        ).toInt()

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.right = spaceWidth
        }
    }
}

