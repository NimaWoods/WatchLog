package com.nimawoods.watchlog.ui

import ListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nimawoods.watchlog.R
import com.nimawoods.watchlog.models.ListItem

class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: ListAdapter
    private lateinit var items: List<ListItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        searchView = view.findViewById(R.id.search_view)

        // call api get newest series

        items = listOf(
            ListItem(R.drawable.ic_launcher_background, "Titel 1", "Beschreibung 1"),
            ListItem(R.drawable.ic_launcher_foreground, "Titel 2", "Beschreibung 2"),
            ListItem(R.drawable.ic_launcher_background, "Titel 3", "Beschreibung 3")
        )

        adapter = ListAdapter(items)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = items.filter {
                    it.title.contains(newText ?: "", ignoreCase = true)
                }
                adapter.updateList(filteredList)
                return true
            }
        })

        return view
    }
}
