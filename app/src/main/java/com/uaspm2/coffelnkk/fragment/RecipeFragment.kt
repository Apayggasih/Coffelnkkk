package com.uaspm2.coffelnkk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.uaspm2.coffelnkk.adapters.RecipeAdapter
import com.uaspm2.coffelnkk.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private lateinit var coffeeAdapter: RecipeAdapter
    private lateinit var milkAdapter: RecipeAdapter
    private lateinit var teaAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)

        coffeeAdapter = RecipeAdapter("coffeBased") { /* onItemClick implementation */ }
        milkAdapter = RecipeAdapter("milkBased") { /* onItemClick implementation */ }
        teaAdapter = RecipeAdapter("teaBased") { /* onItemClick implementation */ }

        binding.RecyclerViewItemCoffee.adapter = coffeeAdapter
        binding.RecyclerViewItemMilk.adapter = milkAdapter
        binding.RecyclerViewItemTea.adapter = teaAdapter

        // Observe changes and submit list when it's updated
        coffeeAdapter.observeRecipeList(viewLifecycleOwner, Observer {
            coffeeAdapter.submitList(it)
        })

        milkAdapter.observeRecipeList(viewLifecycleOwner, Observer {
            milkAdapter.submitList(it)
        })

        teaAdapter.observeRecipeList(viewLifecycleOwner, Observer {
            teaAdapter.submitList(it)
        })

        binding.viewAllcoffee.setOnClickListener{

            findNavController().navigate(
                RecipeFragmentDirections
                    .actionRecipeFragmentToRecipeVerticalFragment())

        }

        binding.viewAllmilk.setOnClickListener{

            findNavController().navigate(
                RecipeFragmentDirections
                    .actionRecipeFragmentToRecipeVerticalFragment())

        }

        binding.viewAlltea.setOnClickListener{

            findNavController().navigate(
                RecipeFragmentDirections
                    .actionRecipeFragmentToRecipeVerticalFragment())

        }

        return binding.root
    }
}