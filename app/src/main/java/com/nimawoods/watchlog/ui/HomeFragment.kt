package com.nimawoods.watchlog.ui

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nimawoods.watchlog.R
import com.nimawoods.watchlog.adapter.CalendarAdapter
import com.nimawoods.watchlog.adapter.WatchlistAdapter
import com.nimawoods.watchlog.models.CalendarItem
import com.nimawoods.watchlog.models.WatchlistItem

class HomeFragment : Fragment() {

    private lateinit var btnFilme: Button
    private lateinit var btnSerien: Button
    private lateinit var watchlistAdapter: WatchlistAdapter
    private var isFilmeSelected = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        btnFilme = view.findViewById(R.id.btn_filme)
        btnSerien = view.findViewById(R.id.btn_serien)

        val calendarItems = listOf(
            CalendarItem("Mo", "23"),
            CalendarItem("Di", "24"),
            CalendarItem("Mi", "25"),
            CalendarItem("Do", "26"),
            CalendarItem("Fr", "27")
        )

        val calendarRecyclerView = view.findViewById<RecyclerView>(R.id.calendar_recycler)
        val gridLayoutManager = GridLayoutManager(requireContext(), calendarItems.size)
        calendarRecyclerView.layoutManager = gridLayoutManager
        calendarRecyclerView.adapter = CalendarAdapter(calendarItems) { selectedItem ->
            println("Selected: ${selectedItem.date}")
        }

        calendarRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // Abstand zwischen Items hinzufügen
        calendarRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(requireContext()))

        val watchlistRecycler: RecyclerView = view.findViewById(R.id.watchlist_recycler)
        val watchlistItemsFilme = listOf(
            WatchlistItem("Inception", "Movie", "Ein spannender Traum in einem Traum.", R.drawable.ic_launcher_foreground, isWatched = false),
            WatchlistItem("Titanic", "Movie", "Ein episches Liebesdrama auf hoher See.", R.drawable.ic_launcher_background, isWatched = true)
        )
        val watchlistItemsSerien = listOf(
            WatchlistItem("Breaking Bad", "S05 | E14", "Die Entwicklung eines Lehrers zum Drogenboss.", R.drawable.ic_launcher_foreground, isWatched = false),
            WatchlistItem("Stranger Things", "S04 | E01", "Ein mysteriöses Abenteuer in Hawkins.", R.drawable.ic_launcher_background, isWatched = true)
        )

        val watchlistRecyclerView = view.findViewById<RecyclerView>(R.id.watchlist_recycler)
        watchlistAdapter = WatchlistAdapter(watchlistItemsSerien) { item ->
            println("Clicked: ${item.title}")
        }

        watchlistRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        watchlistRecyclerView.adapter = watchlistAdapter

        btnFilme.setOnClickListener {
            if (!isFilmeSelected) {
                isFilmeSelected = true
                updateButtonStyles()
                watchlistAdapter.updateList(watchlistItemsFilme)
            }
        }

        btnSerien.setOnClickListener {
            if (isFilmeSelected) {
                isFilmeSelected = false
                updateButtonStyles()
                watchlistAdapter.updateList(watchlistItemsSerien)
            }
        }

        return view
    }

    private fun updateButtonStyles() {
        if (isFilmeSelected) {
            btnFilme.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.purple))
            btnSerien.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.front))
        } else {
            btnSerien.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.purple))
            btnFilme.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.front))
        }
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
