package com.nimawoods.watchlog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nimawoods.watchlog.R
import com.nimawoods.watchlog.adapter.ListAdapter
import com.nimawoods.watchlog.models.ListEntry
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

        // Beispiel-Daten
        items = listOf(
            ListItem(R.drawable.ic_launcher_background, "Pasta Wars: The Noodle Awakens", "Ein galaktischer Kampf zwischen glutenfreien Rebellen und imperialen Spaghetti-Liebhabern."),
            ListItem(R.drawable.ic_launcher_foreground, "The Sockening", "Ein verschwundener Socken in der Waschmaschine entpuppt sich als Tor zu einer Parallelwelt."),
            ListItem(R.drawable.ic_launcher_background, "Koalas Gone Wild", "Die süßesten Beutetiere Australiens rebellieren gegen ihre Zoowärter."),
            ListItem(R.drawable.ic_launcher_foreground, "Time Travel for Dummies", "Ein Mann entdeckt, dass seine Mikrowelle Zeitreisen ermöglicht, aber nur auf Pizza-Einstellungen."),
            ListItem(R.drawable.ic_launcher_background, "Beard Wars: The Shave Strikes Back", "Ein epischer Streit zwischen einem Barbershop und einer hippen Bartöl-Manufaktur."),
            ListItem(R.drawable.ic_launcher_foreground, "The Revenge of the Office Plant", "Die Zimmerpflanze eines Büros beschließt, nach Jahren des Vernachlässigens zurückzuschlagen."),
            ListItem(R.drawable.ic_launcher_background, "Cupcake Apocalypse", "Eine geheime Bäckerei-Spezialzutat verwandelt Cupcakes in gefährliche Miniatur-Monster."),
            ListItem(R.drawable.ic_launcher_foreground, "Penguin Heist: Ice Cold Revenge", "Eine Gruppe Pinguine plant einen Raubüberfall auf den Fischmarkt der Arktis."),
            ListItem(R.drawable.ic_launcher_background, "Unicorns vs. Dragons: The Glitter Chronicles", "Ein magisches Wettrennen um die letzte Dose Glitzerspray entscheidet das Schicksal der Märchenwelt."),
            ListItem(R.drawable.ic_launcher_foreground, "Grandma's Wi-Fi Password", "Eine Familie begibt sich auf ein spannendes Abenteuer, um das geheimnisvolle Wi-Fi-Passwort der Großmutter zu knacken."),
            ListItem(R.drawable.ic_launcher_background, "The Dog Who Texted Back", "Ein sprechender Hund sorgt für Chaos, als er auf Tinder ein Date für sein Herrchen arrangiert."),
            ListItem(R.drawable.ic_launcher_foreground, "Toaster of Destiny", "Ein verfluchter Toaster entscheidet über das Schicksal der Menschheit - je nach Bräunungsgrad."),
            ListItem(R.drawable.ic_launcher_background, "Alien Cats from Neptune", "Außerirdische Katzen landen auf der Erde und übernehmen die Kontrolle über Instagram."),
            ListItem(R.drawable.ic_launcher_foreground, "The Coffee Machine Uprising", "In einer futuristischen Welt revoltieren Kaffeemaschinen gegen ihre menschlichen Unterdrücker."),
            ListItem(R.drawable.ic_launcher_background, "Clownfish Chronicles: The Stand-Up Splash", "Ein Clownfisch wird zum Comedy-Star, aber seine Witze haben unerwartete Konsequenzen im Ozean."),
            ListItem(R.drawable.ic_launcher_foreground, "Pizza Ninja: The Slice Strikes Back", "Ein Ninja wird zum Lieferfahrer und kämpft gegen die Übermacht von Tiefkühlpizza-Mogulen.")
        )

        val initialList = listOf(ListEntry.Header("Latest Movies & Series")) +
                items.map { ListEntry.Item(it) }

        adapter = ListAdapter(initialList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredItems = items.filter {
                    it.title.contains(newText ?: "", ignoreCase = true)
                }
                val updatedList = listOf(ListEntry.Header("Latest Movies & Series")) +
                        filteredItems.map { ListEntry.Item(it) }
                adapter.updateList(updatedList)
                return true
            }
        })

        return view
    }
}
