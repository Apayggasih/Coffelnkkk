package com.uaspm2.coffelnkk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uaspm2.coffelnkk.R
import com.uaspm2.coffelnkk.adapters.ManualBrewAdapter

class ManualBrewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_manual_brew, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.RecyclerViewItem)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        val adapter = ManualBrewAdapter { deskripsiInfo ->

            findNavController().navigate(
                ManualBrewFragmentDirections
                    .actionManualBrewFragmentToDeskripsiFragment(deskripsiInfo))
        }

        recyclerView.adapter = adapter

        return view
    }
}
