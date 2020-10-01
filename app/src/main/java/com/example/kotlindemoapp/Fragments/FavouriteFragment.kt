package com.panaceasoft.pskotlinmaterial.fragment.uicomponent.container.tablayoutwithicon

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.fragment.app.Fragment
import com.example.kotlindemoapp.R

class FavouriteFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ui_container_tab_layout_with_icon_favourite_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}// Required empty public constructor
