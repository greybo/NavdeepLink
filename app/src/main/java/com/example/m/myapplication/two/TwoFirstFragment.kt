package com.example.m.myapplication.two

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.m.myapplication.R
import kotlinx.android.synthetic.main.fragment_two_first.view.*


class TwoFirstFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_two_first, container, false)
        val value= arguments?.getString("arg")
        view.plus_two_button.text = "two fr button $value"
        return view
    }

}