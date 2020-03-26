package com.example.m.myapplication.two

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.m.myapplication.R
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.fragment_blank.view.*

class TwoThirdFragment : Fragment() {

    var navController: NavController? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        button_fragment.setOnClickListener {
//            navController?.navigate(R.id.action_second)
            navController?.popBackStack()
            navController?.popBackStack()
        }
        val value = arguments?.getString("arg")
        view.button_fragment.text = "back to start ${value}"
        text_fragment.text = "3-3"
    }

}
