@file:Suppress("DEPRECATION")

package it.programmazionemobile.olympicgames2024.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import it.programmazionemobile.olympicgames2024.R

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.menu_bar)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(
            bottomNavigationView, navController
        )


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {

                    navController.navigate(R.id.nav_home)
                    true
                }

                R.id.nav_profile -> {
                    navController.navigate(R.id.nav_profile)
                    true
                }

                R.id.nav_search -> {
                    navController.navigate(R.id.nav_search)
                    true
                }

                R.id.nav_favourite -> {
                    navController.navigate(R.id.nav_favourite)
                    true
                }

                R.id.nav_logout -> {
                    navController.navigate(R.id.nav_logout)
                    true
                }

                else -> false
            }
        }


    }
}
