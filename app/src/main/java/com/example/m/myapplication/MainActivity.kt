package com.example.m.myapplication

import android.app.SearchManager
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.m.myapplication.one.OpenDialogListener
import kotlinx.android.synthetic.main.activity_main.*


const val TAG_NAV = "nav_test"

interface CallbackQueryVoiceListener {
    fun callbackQuery(text: String)
}

class MainActivity : AppCompatActivity(), OpenDialogListener {
    private val JARGON: String = "JARGON"
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
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        supportActionBar?.elevation = 0f
//        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
//        search_view.setSearchableInfo(searchManager.getSearchableInfo(componentName))

    }

    override fun onBackPressed() {
//        super.onBackPressed()
        navController.popBackStack()
    }

    override fun onSearchRequested(): Boolean {
        val appData = Bundle().apply {
            putBoolean(JARGON, true)
        }
        startSearch(null, false, appData, false)
        return true
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            (getCurrentFragment() as? CallbackQueryVoiceListener)?.callbackQuery(query)
        }
    }

    private fun getCurrentFragment(): Fragment? {
        val navHost = supportFragmentManager.findFragmentById(R.id.main_host_fragment)
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                return fragment
            }
        }
        return null
    }

    override fun openSearch() {
        onSearchRequested()
    }

    override fun openDialogVoise() {
        val intent = Intent(Intent.CATEGORY_VOICE)
        startActivity(intent)
    }
}
