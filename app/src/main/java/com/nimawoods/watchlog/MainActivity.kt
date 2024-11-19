package com.nimawoods.watchlog

import android.os.Bundle
import android.widget.Adapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nimawoods.watchlog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lvTodoList: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var shoppingItems: ArrayList<String>
    private lateinit var itemAdapter: ArrayList<Adapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}