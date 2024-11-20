package com.nimawoods.watchlog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nimawoods.watchlog.R
import com.nimawoods.watchlog.models.ListEntry
import com.nimawoods.watchlog.models.ListItem

class ListAdapter(private var items: List<ListEntry>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ListEntry.Header -> VIEW_TYPE_HEADER
            is ListEntry.Item -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(view)
            }
            VIEW_TYPE_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list, parent, false)
                ItemViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val entry = items[position]) {
            is ListEntry.Header -> (holder as HeaderViewHolder).bind(entry.text)
            is ListEntry.Item -> (holder as ItemViewHolder).bind(entry.listItem)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newItems: List<ListEntry>) {
        items = newItems
        notifyDataSetChanged()
    }

    // Header ViewHolder
    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerText: TextView = itemView.findViewById(R.id.header_text)

        fun bind(text: String) {
            headerText.text = text
        }
    }

    // Item ViewHolder
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.item_image)
        private val title: TextView = itemView.findViewById(R.id.item_title)
        private val description: TextView = itemView.findViewById(R.id.item_description)

        fun bind(item: ListItem) {
            image.setImageResource(item.imageRes)
            title.text = item.title
            description.text = item.description
        }
    }
}
