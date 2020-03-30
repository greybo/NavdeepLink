package com.example.m.myapplication.one

import android.annotation.SuppressLint
import android.app.SearchManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.m.myapplication.CallbackQueryVoiceListener
import com.example.m.myapplication.MainActivity
import com.example.m.myapplication.R
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.fragment_blank.view.*


class ThirdFragment : Fragment(), CallbackQueryVoiceListener {

    var navController: NavController? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //this need for creating a menu in the fragment
        setHasOptionsMenu(true)

        navController = NavHostFragment.findNavController(this)
        button_fragment.setOnClickListener {
            navController?.popBackStack()
            navController?.popBackStack()
        }
        val value = arguments?.getString("arg")
        view.button_fragment.text = "back to start ${value}"
        text_fragment.text = "3"

        val searchManager =
            activity?.getSystemService(AppCompatActivity.SEARCH_SERVICE) as SearchManager
        search_view.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
    }

    override fun onResume() {
        super.onResume()
        search_view.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        search_view.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

        val actionBar: ActionBar? = (activity as MainActivity).supportActionBar
        actionBar?.title = "Third Fragment"
//        actionBar?.setHomeAsUpIndicator(android.R.drawable.ic_delete)
        actionBar?.setDisplayShowTitleEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun callbackQuery(text: String) {
        search_view.setQuery(text, false)
    }
}
