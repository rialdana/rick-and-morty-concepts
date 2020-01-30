package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Getting the navHostFragment instance
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Getting the navController
        val navController = host.navController

        // Getting the bottom nav instance
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Setting up the bottomNav with the navController so we can navigate
        bottomNav?.setupWithNavController(navController)
    }
}
