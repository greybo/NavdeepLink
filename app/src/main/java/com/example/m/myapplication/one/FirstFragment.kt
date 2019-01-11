package com.example.m.myapplication.one

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.m.myapplication.R
import kotlinx.android.synthetic.main.fragment_one_first.view.*
import java.lang.IllegalArgumentException

class FirstFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one_first, container, false)
        val value= arguments?.getString("arg")
        view.plus_one_button.text = "one fr button $value"
        return view
    }

     fun oneClick(v:View){
    }
}