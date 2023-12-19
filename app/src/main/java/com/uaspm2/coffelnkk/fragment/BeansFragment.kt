package com.uaspm2.coffelnkk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uaspm2.coffelnkk.adapters.BeansAdapter
import com.uaspm2.coffelnkk.databinding.FragmentBeansBinding

class BeansFragment : Fragment() {

    private lateinit var binding: FragmentBeansBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeansBinding.inflate(inflater, container, false)

        binding.RecyclerViewItem.adapter = BeansAdapter { deskripsiInfo ->

            findNavController().navigate(
                BeansFragmentDirections
                    .actionBeansFragmentToDeskripsiFragment(deskripsiInfo))

        }
        return binding.root
    }
}