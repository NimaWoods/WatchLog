package com.nimawoods.watchlog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nimawoods.watchlog.R
import com.nimawoods.watchlog.models.WatchlistItem

class WatchlistAdapter(
    private var items: List<WatchlistItem>,
    private val onClick: (WatchlistItem) -> Unit
) : RecyclerView.Adapter<WatchlistAdapter.WatchlistViewHolder>() {

    inner class WatchlistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val episode: TextView = view.findViewById(R.id.episode)
        val description: TextView = view.findViewById(R.id.description)
        val image: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_watchlist, parent, false)
        return WatchlistViewHolder(view)
    }

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.episode.text = item.seasondAndEpisodeString
        holder.description.text = item.description

        Glide.with(holder.itemView.context)
            .load(item.imageURL)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.image)

        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = items.size

    fun updateList(newItems: List<WatchlistItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
