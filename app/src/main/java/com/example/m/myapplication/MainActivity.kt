package com.example.m.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

const val TAG_NAV = "nav_test"

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(this, R.id.main_host_fragment)
//        navController.graph = navController.navInflater.inflate(R.navigation.main_navigation)
//        NavigationUI.setupWithNavController(main_bottom_nav_bar, navController)

        main_bottom_nav_bar.setOnNavigationItemSelectedListener { menuItem ->
            Log.i(TAG_NAV, "${menuItem.itemId} == ${R.id.one_navigation}")
            navController.graph = when (menuItem.itemId) {
                R.id.one_navigation -> navController.navInflater.inflate(R.navigation.one_navigation)
                R.id.two_navigation -> navController.navInflater.inflate(R.navigation.two_navigation)
                else -> navController.navInflater.inflate(R.navigation.one_navigation)
            }

            true
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        navController.popBackStack()
    }
}
