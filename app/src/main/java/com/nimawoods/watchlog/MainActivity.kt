package com.nimawoods.watchlog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NavHostFragment und NavController initialisieren
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        // BottomNavigationView mit NavController verknüpfen
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    println("HomeFragment selected")
                }
                R.id.searchFragment -> {
                    println("SearchFragment selected")
                }
                R.id.watchlistFragment -> {
                    println("WatchlistFragment selected")
                }
                R.id.profileFragment -> {
                    println("ProfileFragment selected")
                }
            }
            true
        }


        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }
}
