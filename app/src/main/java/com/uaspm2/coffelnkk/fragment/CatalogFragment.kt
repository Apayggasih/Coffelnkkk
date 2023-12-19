package com.uaspm2.coffelnkk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uaspm2.coffelnkk.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment() {

    private lateinit var binding: FragmentCatalogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogBinding.inflate(inflater, container, false)


        binding.catalog1.setOnClickListener {
            this.findNavController().navigate(
                MainFragmentDirections
                    .actionMainFragmentToBeansFragment())
        }

        binding.catalog2.setOnClickListener {
            this.findNavController().navigate(
                MainFragmentDirections
                    .actionMainFragmentToRecipeFragment()
            )
        }

        binding.catalog3.setOnClickListener {
            this.findNavController().navigate(
                MainFragmentDirections
                    .actionMainFragmentToManualBrewFragment()
            )
        }

        return(binding.root)
    }
}