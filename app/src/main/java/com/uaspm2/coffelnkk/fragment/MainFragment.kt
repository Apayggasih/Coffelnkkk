package com.uaspm2.coffelnkk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uaspm2.coffelnkk.R
import com.uaspm2.coffelnkk.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    private val fragmentHome: Fragment = HomeFragment()
    private val fragmentCatalog: Fragment = CatalogFragment()
    private val fragmentProfile: Fragment = ProfileFragment()
    private var active: Fragment = fragmentHome

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        setButtomNav()
        return(binding.root)
    }

    private fun setButtomNav() {
        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> callFragment(fragmentHome)
                R.id.nav_catalog -> callFragment(fragmentCatalog)
                R.id.nav_profile -> callFragment(fragmentProfile)
            }
            true
        }

        if (!fragmentHome.isAdded) {
            childFragmentManager.beginTransaction()
                .add(R.id.container, fragmentHome)
                .commit()
        }
    }

    private fun callFragment(fragment: Fragment) {
        if (fragment.isAdded) {
            childFragmentManager.beginTransaction()
                .hide(active)
                .show(fragment)
                .commit()
            active = fragment
        } else {
            childFragmentManager.beginTransaction()
                .hide(active)
                .add(R.id.container, fragment)
                .commit()
            active = fragment
        }
    }
}