package com.example.m.myapplication.one

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.m.myapplication.CallbackQueryVoiceListener
import com.example.m.myapplication.MainActivity
import com.example.m.myapplication.R
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.fragment_blank.view.*
import java.lang.reflect.Field

interface OpenDialogListener {
    fun openSearch()
    fun openDialogVoise()

}

class SecondFragment : Fragment(), CallbackQueryVoiceListener {

    var navController: NavController? = null
    var viewModel: SecondViewModel? = null
    var searchView: SearchView? = null
    var openDialogListener: OpenDialogListener? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        openDialogListener = activity as? OpenDialogListener
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
//        this need for creating a menu in the fragment
        setHasOptionsMenu(true)
        navController = NavHostFragment.findNavController(this)
        button_fragment.setOnClickListener {
            navController?.navigate(R.id.action_to_third)
        }
        val value = arguments?.getString("arg")
        view.button_fragment.text = "one fr button $value"
        text_fragment.text = "2"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

        val actionBar: ActionBar? = (activity as MainActivity).supportActionBar
        actionBar?.title = "Second Fragment"

        actionBar?.setDisplayShowTitleEnabled(true)//false
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        inflater.inflate(R.menu.menu_search_icon, menu)
        /*   inflater.inflate(R.menu.menu_search, menu)

           val searchItem = menu.findItem(R.id.action_search)
           searchView = searchItem?.actionView as SearchView
           changeColor(searchView)

           searchView?.queryHint = getString(R.string.search)

           val searchManager =
               getSystemService(context!!, SearchManager::class.java)
           searchView?.setSearchableInfo(searchManager?.getSearchableInfo(activity?.componentName))*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
            R.id.action_open -> openDialogListener?.openDialogVoise()
        }
        return super.onOptionsItemSelected(item)
    }

    fun changeColor(searchView: SearchView?) {
        searchView ?: return
        //change icon color
        val closeIcon = searchView.findViewById(R.id.search_close_btn) as ImageView
        closeIcon.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                android.R.drawable.ic_secure
            )
        )
        val voiceIcon = searchView.findViewById(R.id.search_voice_btn) as ImageView
        voiceIcon.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                android.R.drawable.arrow_up_float
            )
        )


//        val searchiconId =
//            view!!.context.resources.getIdentifier("android:id/search_button", null, null)
//        val imgView: ImageView? = searchView.findViewById(searchiconId) as? ImageView

        /*  var searchField: Field = SearchView::class.java.getDeclaredField("mCloseButton")
          searchField.setAccessible(true)
          val imgView =searchField.get(searchView) as ImageView

          val whiteIcon: Drawable? = imgView?.getDrawable()
          whiteIcon?.setTint(Color.RED) //Whatever color you want it to be

          imgView?.setImageDrawable(whiteIcon)*/
    }

    private fun setCloseSearchIcon(searchView: SearchView) {
        try {
            var searchField: Field =
                SearchView::class.java.getDeclaredField("mCloseButton")
            searchField.setAccessible(true)
            val closeBtn =
                searchField.get(searchView) as ImageView
            closeBtn.setImageResource(android.R.drawable.ic_btn_speak_now)
            searchField =
                SearchView::class.java.getDeclaredField("mVoiceButton")
            searchField.setAccessible(false)
            val voiceBtn =
                searchField.get(searchView) as ImageView
            voiceBtn.setImageResource(android.R.drawable.ic_btn_speak_now)
        } catch (e: NoSuchFieldException) {
            Log.e("SearchView", e.message, e)
        } catch (e: IllegalAccessException) {
            Log.e("SearchView", e.message, e)
        }
    }

    override fun callbackQuery(text: String) {
        searchView?.setQuery(text, false)
    }
}
