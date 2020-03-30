package com.example.m.myapplication.one

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.m.myapplication.MainActivity
import com.example.m.myapplication.R
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.fragment_blank.view.*


class FirstFragment : Fragment() {
    var navController: NavController? = null
    var searchView: SearchView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val actionBar = (activity as MainActivity).supportActionBar
//        actionBar?.show()
       //this need for creating a menu in the fragment
        setHasOptionsMenu(true)
        navController = NavHostFragment.findNavController(this)
        button_fragment.setOnClickListener {
            navController?.navigate(R.id.action_to_second)
        }
        val value = arguments?.getString("arg")
        view.button_fragment.text = "one fr button $value"
        text_fragment.text = "1"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

//        // Inflate the menu; this adds items to the action bar if it is present.
//        inflater.inflate(R.menu.menu_main, menu)

        val actionBar: ActionBar? = (activity as MainActivity).supportActionBar
        actionBar?.title = "First Fragment"
        actionBar?.setDisplayShowTitleEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(false)

        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
//      changeColor(searchView)
        searchView?.queryHint = getString(R.string.search)

        val searchManager =
            activity?.getSystemService(SEARCH_SERVICE) as? SearchManager
        searchView?.setSearchableInfo(searchManager?.getSearchableInfo(activity?.componentName))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}

