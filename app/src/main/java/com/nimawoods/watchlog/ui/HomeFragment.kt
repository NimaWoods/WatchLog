package com.nimawoods.watchlog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nimawoods.watchlog.R
import com.nimawoods.watchlog.adapter.WatchlistAdapter
import com.nimawoods.watchlog.models.WatchlistItem

class HomeFragment : Fragment() {

    private lateinit var btnFilme: Button
    private lateinit var btnSerien: Button
    private lateinit var watchlistAdapter: WatchlistAdapter
    private var isFilmeSelected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        btnFilme = view.findViewById(R.id.btn_filme)
        btnSerien = view.findViewById(R.id.btn_serien)

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

        btnFilme.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.front))
        btnSerien.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.purple))

        // Set initial list to Serien
        watchlistAdapter.updateList(watchlistItemsSerien)

        // Button click listeners
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
}
