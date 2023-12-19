package com.uaspm2.coffelnkk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uaspm2.coffelnkk.databinding.FragmentRecipeVerticalBinding

class RecipeVerticalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecipeVerticalBinding.inflate(inflater, container, false)

//        binding.itemJudul.text = deskripsiInfo.title
//        binding.itemDeskripsi.text = deskripsiInfo.content
//        Firebase.storage.getReference(deskripsiInfo.image).downloadUrl.addOnSuccessListener {
//            Glide.with(binding.root.context)
//                .load(it)
//                .into(binding.itemGambar)
//        }
        return binding.root
    }
}