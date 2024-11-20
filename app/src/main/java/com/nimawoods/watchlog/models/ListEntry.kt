package com.nimawoods.watchlog.models

sealed class ListEntry {
    data class Header(val text: String) : ListEntry()
    data class Item(val listItem: ListItem) : ListEntry()
}
